package com.njit.xydl.life.express.service.impl;

import com.njit.xydl.life.common.enums.StatusEnum;
import com.njit.xydl.life.common.util.UserUtil;
import com.njit.xydl.life.express.dao.ExpressMapper;
import com.njit.xydl.life.common.entity.Express;
import com.njit.xydl.life.express.service.ExpressService;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


/**
 * @author HanYehong
 */
@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public List<Express> listExpressOrderByStatus(Integer status) {
        return expressMapper.selectExpressOrderByStatus(status);
    }

    @Override
    public List<Express> listExpressOrderByStatusAndPublishor(Integer status)
            throws GatewayException {
        if (status == null){
            throw new GatewayException("请指明查询的订单状态");
        }
        String openId = UserUtil.getCurrentUserId();
        return expressMapper.selectExpressOrderByStatusAndPublishor(status, openId);
    }

    @Override
    public List<Express> listDoingOrderByPublishor() throws GatewayException {
        List<Express> list1 = listExpressOrderByStatusAndPublishor(StatusEnum.WAIT_ACCEPT.getCode());
        List<Express> list2 = listExpressOrderByStatusAndPublishor(StatusEnum.WAIT_ACCEPTOR_PAY.getCode());
        List<Express> list3 = listExpressOrderByStatusAndPublishor(StatusEnum.WAIT_PUBLISHOR_PAY.getCode());
        List<Express> list4 = listExpressOrderByStatusAndPublishor(StatusEnum.WAIT_CONFIRM.getCode());
        List<Express> list5 = listExpressOrderByStatusAndPublishor(StatusEnum.WAIT_SEND.getCode());
        list1.addAll(list2);
        list1.addAll(list3);
        list1.addAll(list4);
        list1.addAll(list5);
        list1.sort(Comparator.comparing(Express::getCreate_time));
        return list1;
    }

    @Override
    public void addNewExpressOrder(Express express) {
        
    }

    @Override
    public void catchOrder(String orderNumber) {

    }

    @Override
    public void cancelOrder(String orderNumber) {

    }

    @Override
    public void payOrder(String orderNumber) {

    }

    @Override
    public void sended(String orderNumber) {

    }

    @Override
    public void received(String orderNumber) {

    }

    @Override
    public void authorization(String orderNumber) {

    }
}
