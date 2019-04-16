package com.njit.xydl.life.express.service.impl;

import com.njit.xydl.life.common.entity.Express;
import com.njit.xydl.life.common.enums.StatusEnum;
import com.njit.xydl.life.common.util.UserUtil;
import com.njit.xydl.life.express.dao.ExpressMapper;
import com.njit.xydl.life.express.service.ExpressService;
import com.njit.xydl.life.express.service.bo.OrderListBO;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private static final int TIMOUT = 10 * 1000;

    private static final String LOCK_KEY_CATCH = "lock.catchorder";

    @Override
    public List<Express> listExpressOrderByStatus(Integer status) {
        return expressMapper.selectExpressOrderByStatus(status);
    }

    @Override
    public List<Express> listExpressOrderByPublishor() throws GatewayException {
        String openId = UserUtil.getCurrentUserId();
        return expressMapper.selectExpressOrderByStatusAndPublishor(status, openId);
    }

    @Override
    public OrderListBO listDoingOrderByPublishor() throws GatewayException {
        Express param = new Express();
        param.setPublishor(UserUtil.getCurrentUserId());
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
    public void addNewExpressOrder(Express express) throws GatewayException {
        express.setPublishor(UserUtil.getCurrentUserId());
        expressMapper.insertSelective(express);
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
    public void cancelOrder(String orderNumber) throws GatewayException {
        String openId = UserUtil.getCurrentUserId();
        Express express = expressMapper.selectByOrderNumber(orderNumber);
        if (express.getPublishor().equals(openId)) {
            // 进行汇款
        }

        if (express.getAcceptor().equals(openId)) {
            express.setStatus(StatusEnum.WAIT_ACCEPTOR_PAY.getCode());
            express.setUpdateTime(new Date());
        }

        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void payOrder(String orderNumber) {

    }

    @Override
    public void sended(String orderNumber) {
        Express express = expressMapper.selectByOrderNumber(orderNumber);
        express.setStatus(StatusEnum.WAIT_CONFIRM.getCode());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void received(String orderNumber) {
        Express express = expressMapper.selectByOrderNumber(orderNumber);
        express.setStatus(StatusEnum.COMPLETE.getCode());
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public void authorization(String orderNumber) {
        Express express = expressMapper.selectByOrderNumber(orderNumber);
        express.setStatus(StatusEnum.WAIT_SEND.getCode());
        expressMapper.updateByPrimaryKeySelective(express);
    }
}
