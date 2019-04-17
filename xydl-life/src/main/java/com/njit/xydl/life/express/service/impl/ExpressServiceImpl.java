package com.njit.xydl.life.express.service.impl;

import com.njit.xydl.life.common.entity.Express;
import com.njit.xydl.life.common.enums.StatusEnum;
import com.njit.xydl.life.common.feignservice.PayService;
import com.njit.xydl.life.common.feignservice.dto.PayDTO;
import com.njit.xydl.life.common.util.UserUtil;
import com.njit.xydl.life.express.dao.ExpressMapper;
import com.njit.xydl.life.express.service.ExpressService;
import com.njit.xydl.life.express.service.bo.OrderListBO;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
import jdk.net.SocketFlow;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author HanYehong
 */
@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressMapper expressMapper;

    @Autowired
    private PayService payService;

    private static final int TIMOUT = 10 * 1000;

    private static final String LOCK_KEY_CATCH = "lock.catchorder";

    @Override
    public List<Express> listExpressOrderByStatus(Integer status) {
        return expressMapper.selectExpressOrderByStatus(status);
    }

    @Override
    public OrderListBO listAllOrderByPublishor() throws GatewayException {
        Express param = new Express();
        param.setPublishor(UserUtil.getCurrentUserId());
        return listAllOrderByPerson(param);
    }

    @Override
    public OrderListBO listAllOrderByAcceptor() throws GatewayException {
        Express param = new Express();
        param.setAcceptor(UserUtil.getCurrentUserId());
        return listAllOrderByPerson(param);
    }

    private OrderListBO listAllOrderByPerson(Express param) {
        List<Express> allList = expressMapper.selectSelective(param);
        List<Express> processing = allList.stream().filter(x ->
                x.getStatus().equals(StatusEnum.WAIT_ACCEPT.getCode()) ||
                        x.getStatus().equals(StatusEnum.WAIT_CONFIRM.getCode()) ||
                        x.getStatus().equals(StatusEnum.WAIT_SEND.getCode()) ||
                        x.getStatus().equals(StatusEnum.WAIT_AUTHORIZATION.getCode())).collect(Collectors.toList());
        List<Express> complete = allList.stream().filter(x -> x.getStatus().equals(StatusEnum.COMPLETE.getCode())).collect(Collectors.toList());
        List<Express> uncomplete = allList.stream().filter(x -> x.getStatus().equals(StatusEnum.UN_COMPLETE.getCode())).collect(Collectors.toList());
        OrderListBO result = new OrderListBO();
        result.setProcessing(processing);
        result.setComplete(complete);
        result.setUncomplete(uncomplete);
        return result;
    }

    @Override
    public void catchOrder(String orderNumber) throws GatewayException {
        //加锁
        long time = System.currentTimeMillis() + UUID.randomUUID().hashCode() + TIMOUT;
        if (!RedisHelper.getRedisUtil().lock(LOCK_KEY_CATCH, String.valueOf(time))) {
            throw new GatewayException("该订单已经被接单啦，试试其它的吧~");
        }
        Express express = expressMapper.selectByOrderNumber(orderNumber);
        if (StringUtils.isBlank(express.getAcceptor())) {
            throw new GatewayException("该订单已经被接单啦，试试其它的吧~");
        }
        if (express.getPublishor().equals(UserUtil.getCurrentUserId())) {
            throw new GatewayException("不能接自己的单子哦~");
        }
        try {
            express.setAcceptor(UserUtil.getCurrentUserId());
            express.setAcceptTime(new Date());
            express.setStatus(StatusEnum.WAIT_AUTHORIZATION.getCode());
            expressMapper.updateByPrimaryKeySelective(express);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            RedisHelper.getRedisUtil().unlock(LOCK_KEY_CATCH, String.valueOf(time));
        }

    }

    @Override
    public void authorization(String orderNumber) throws GatewayException {
        Express express = getExpressOrder(orderNumber);
        express.setStatus(StatusEnum.WAIT_SEND.getCode());
        express.setUpdateTime(new Date());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void refuseCurrentAccept(String orderNumber) throws GatewayException {
        Express express = getExpressOrder(orderNumber);
        if (!express.getStatus().equals(StatusEnum.WAIT_AUTHORIZATION.getCode())) {
            throw new GatewayException("非待授权订单不允许被拒绝接单");
        }
        int result = publishAgain(express);
        if (result < 1) {
            throw new GatewayException("系统不稳定，请稍后再试~");
        }
        // 发送短信给接单者
    }

    @Override
    public void sended(String orderNumber) throws GatewayException {
        Express express = getExpressOrder(orderNumber);
        express.setStatus(StatusEnum.WAIT_CONFIRM.getCode());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void received(String orderNumber) throws GatewayException {
        Express express = getExpressOrder(orderNumber);
        express.setStatus(StatusEnum.COMPLETE.getCode());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void cancelOrder(String orderNumber) throws GatewayException {
        String openId = UserUtil.getCurrentUserId();
        Express express = getExpressOrder(orderNumber);
        if (express.getStatus().equals(StatusEnum.WAIT_AUTHORIZATION.getCode()) || express.getStatus().equals(StatusEnum.WAIT_ACCEPT.getCode())) {
            // 金额回流至发布者账户
            PayDTO param = new PayDTO();
            param.setTargetAccount(express.getPublishor());
            param.setMoney(express.getPrice());
            payService.payTemporaryToPerson(param);
        } else if (express.getStatus().equals(StatusEnum.WAIT_SEND.getCode())){
            if (openId.equals(express.getPublishor())) {
                // 从中间账户汇款至接单者
                PayDTO param = new PayDTO();
                param.setTargetAccount(express.getAcceptor());
                param.setMoney(express.getPrice());
                payService.payTemporaryToPerson(param);
            }
            if (openId.equals(express.getAcceptor())) {
                // 进行打款操作  接单者账户--->发布者账户
                PayDTO param = new PayDTO();
                param.setAccount(express.getAcceptor());
                param.setTargetAccount(express.getPublishor());
                param.setMoney(express.getPrice());
                payService.payTemporaryToPerson(param);
            }
        } else {
            throw new GatewayException("此状态下不允许被取消");
        }
        express.setStatus(StatusEnum.UN_COMPLETE.getCode());
        express.setUpdateTime(new Date());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void publishExpressOrder(Express express) throws GatewayException {
        // 发布者打款至中间账户
        PayDTO param = new PayDTO();
        param.setAccount(UserUtil.getCurrentUserId());
        param.setMoney(express.getPrice());
        payService.payPersonToTemporary(param);
        express.setPublishor(UserUtil.getCurrentUserId());
        express.setOrderNumber(generateOrderNumber());
        express.setStatus(StatusEnum.WAIT_ACCEPT.getCode());
        expressMapper.insertSelective(express);
    }

    @Override
    public void rePublish(String orderNumber) throws GatewayException {
        Express express = getExpressOrder(orderNumber);
        if (!express.getStatus().equals(StatusEnum.UN_COMPLETE.getCode())) {
            throw new GatewayException("非未完成订单不允许被重新发布");
        }
        int result = publishAgain(express);
        if (result < 1) {
            throw new GatewayException("系统不稳定，请稍后再试~");
        }
    }

    private int publishAgain(Express express) {
        express.setAcceptor("");
        express.setAcceptTime(null);
        express.setStatus(StatusEnum.WAIT_ACCEPT.getCode());
        express.setUpdateTime(new Date());
        return expressMapper.updateByPrimaryKeySelective(express);
    }

    private Express getExpressOrder(String orderNumber) throws GatewayException {
        if (StringUtils.isBlank(orderNumber)) {
            throw new GatewayException("订单号为空");
        }
        Express express = expressMapper.selectByOrderNumber(orderNumber);
        if (express == null) {
            throw new GatewayException("该订单不存在");
        }
        return express;
    }

    private String generateOrderNumber() {
        String newNumber = "DL" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + UUID.randomUUID().hashCode();
        System.out.println(newNumber);
        return newNumber;
    }
}
