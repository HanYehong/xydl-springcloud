package com.njit.xydl.life.express.service.impl;

import com.njit.xydl.life.common.entity.Express;
import com.njit.xydl.life.common.enums.StatusEnum;
import com.njit.xydl.life.common.feign.PayService;
import com.njit.xydl.life.common.feign.UserService;
import com.njit.xydl.life.common.feign.dto.OpenIdDTO;
import com.njit.xydl.life.common.feign.dto.PayDTO;
import com.njit.xydl.life.express.dao.ExpressMapper;
import com.njit.xydl.life.express.service.ExpressService;
import com.njit.xydl.life.express.service.SmsSendService;
import com.njit.xydl.life.express.service.bo.OrderListBO;
import com.yehong.han.config.authorization.UserUtil;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.ValidException;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private SmsSendService smsSendService;

    @Autowired
    private PayService payService;

    @Autowired
    private UserService userService;

    private static final int TIMOUT = 10 * 1000;

    private static final String LOCK_KEY_CATCH = "lock.catchorder";

    @Override
    public List<Express> listExpressOrderByStatus(Integer status) {
        return expressMapper.selectExpressOrderByStatus(status);
    }

    @Override
    public OrderListBO listAllOrderByPublishor(){
        Express param = new Express();
        param.setPublishor(UserUtil.getCurrentUserId());
        return listAllOrderByPerson(param);
    }

    @Override
    public OrderListBO listAllOrderByAcceptor() {
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void catchOrder(String orderNumber) {
        checkRealIdentity();
        Express express = expressMapper.selectByOrderNumber(orderNumber);
        if (!StringUtils.isBlank(express.getAcceptor())) {
            throw new ValidException("该订单已经被接单啦，试试其它的吧~");
        }
        if (express.getPublishor().equals(UserUtil.getCurrentUserId())) {
            throw new ValidException("不能接自己的单子哦~");
        }
        if (!express.getStatus().equals(StatusEnum.WAIT_ACCEPT.getCode())) {
            throw new ValidException("此订单状态不允许被接单");
        }
        //加锁
        long time = System.currentTimeMillis() + UUID.randomUUID().hashCode() + TIMOUT;
        try {
            if (!RedisHelper.getRedisUtil().lock(LOCK_KEY_CATCH, String.valueOf(time))) {
                throw new ValidException("该订单已经被接单啦，试试其它的吧~");
            }
            express.setAcceptor(UserUtil.getCurrentUserId());
            express.setAcceptTime(new Date());
            express.setStatus(StatusEnum.WAIT_AUTHORIZATION.getCode());
            expressMapper.updateByPrimaryKeySelective(express);
            smsSendService.sendForAccept(express.getPhone(), express.getOrderNumber());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidException(e.getMessage());
        } finally {
            // 解锁
            RedisHelper.getRedisUtil().unlock(LOCK_KEY_CATCH, String.valueOf(time));
        }

    }

    @Override
    public void authorization(String orderNumber) {
        Express express = getExpressOrder(orderNumber);
        express.setStatus(StatusEnum.WAIT_SEND.getCode());
        express.setUpdateTime(new Date());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void refuseCurrentAccept(String orderNumber) throws IOException {
        Express express = getExpressOrder(orderNumber);
        if (!express.getStatus().equals(StatusEnum.WAIT_AUTHORIZATION.getCode())) {
            throw new ValidException("非待授权订单不允许被拒绝接单");
        }
        int result = publishAgain(express);
        if (result < 1) {
            throw new ValidException("系统不稳定，请稍后再试~");
        }
        smsSendService.sendForRefuse("15189809881");
    }

    @Override
    public void sended(String orderNumber) {
        Express express = getExpressOrder(orderNumber);
        express.setStatus(StatusEnum.WAIT_CONFIRM.getCode());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void received(String orderNumber) {
        Express express = getExpressOrder(orderNumber);
        express.setStatus(StatusEnum.COMPLETE.getCode());
        express.setCompleteTime(new Date());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void cancelOrder(String orderNumber) {
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
                int result = payService.payTemporaryToPerson(param);
                if (result == 0) {
                    throw new ValidException("服务繁忙，请稍后再试");
                }else if (result == 2) {
                    throw new ValidException("余额不足，请充值");
                }
            }
        } else {
            throw new ValidException("此状态下不允许被取消");
        }
        express.setStatus(StatusEnum.UN_COMPLETE.getCode());
        express.setUpdateTime(new Date());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void publishExpressOrder(Express express){
        // 检查实名认证
        checkRealIdentity();
        // 发布者打款至中间账户
        payPersonToTemporary(express.getPrice());
        express.setPublishor(UserUtil.getCurrentUserId());
        express.setOrderNumber(generateOrderNumber());
        express.setStatus(StatusEnum.WAIT_ACCEPT.getCode());
        expressMapper.insertSelective(express);
    }

    @Override
    public void rePublish(String orderNumber) {
        Express express = getExpressOrder(orderNumber);
        if (!express.getStatus().equals(StatusEnum.UN_COMPLETE.getCode())) {
            throw new ValidException("非未完成订单不允许被重新发布");
        }
        int result = publishAgain(express);
        if (result < 1) {
            throw new ValidException("系统不稳定，请稍后再试~");
        }
    }

    private int publishAgain(Express express) {
        express.setAcceptor("");
        express.setAcceptTime(null);
        express.setStatus(StatusEnum.WAIT_ACCEPT.getCode());
        express.setUpdateTime(new Date());
        // 重置发布时间
        express.setCreateTime(new Date());
        // 默认截止时间+1天
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, 1);
        express.setOrderDeadlineDate(c.getTime());
        return expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public Express getExpressOrder(String orderNumber) {
        if (StringUtils.isBlank(orderNumber)) {
            throw new ValidException("订单号为空");
        }
        Express express = expressMapper.selectByOrderNumber(orderNumber);
        if (express == null) {
            throw new ValidException("该订单不存在");
        }
        return express;
    }

    private String generateOrderNumber() {
        String newNumber = "DL" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + Math.abs(UUID.randomUUID().hashCode());
        System.out.println(newNumber);
        return newNumber;
    }

    private Object checkResponse(Response response) {
        if (response.getCode() == Status.FAIL.getCode()) {
            throw new ValidException("服务器繁忙，请稍后再试");
        }
        return response.getData();
    }

    private void checkRealIdentity() {
        OpenIdDTO param = new OpenIdDTO();
        param.setOpenId(UserUtil.getCurrentUserId());
        Response check = userService.checkRealIdentity(param);
        if (Status.FAIL.getCode() == check.getCode() || check.getData() == null) {
            throw new ValidException("哎呀~服务器开小差啦，再试一下？");
        }
        if (check.getData().equals(0)) {
            throw new ValidException("还没有进行实名认证喔~ 实名渠道：个人中心 -> 我的实名认证");
        }
    }

    private void payPersonToTemporary(double money) {
        PayDTO param = new PayDTO();
        param.setAccount(UserUtil.getCurrentUserId());
        param.setMoney(money);
        int result = payService.payPersonToTemporary(param);
        if (result == 0) {
            throw new ValidException("服务繁忙，请稍后再试");
        }else if (result == 2) {
            throw new ValidException("余额不足，请充值");
        }
    }

}
