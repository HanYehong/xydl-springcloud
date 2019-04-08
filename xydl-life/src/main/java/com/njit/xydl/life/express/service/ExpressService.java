package com.njit.xydl.life.express.service;

import com.njit.xydl.life.common.entity.Express;
import com.yehong.han.config.exception.GatewayException;

import java.util.List;

/**
 * @author yehong.han
 */
public interface ExpressService {

    /**
     * 列出所有指定状态的快递单
     * @param status
     * @return
     */
    List<Express> listExpressOrderByStatus(Integer status);

    /**
     * 根据当前的人搜索所有完成、未完成、进行中的订单
     * @param status
     * @return
     */
    List<Express> listExpressOrderByStatusAndPublishor(Integer status) throws GatewayException;

    /**
     * 查询当前人发布的所有进行中的订单
     * @return
     */
    List<Express> listDoingOrderByPublishor() throws GatewayException;

    /**
     * 增加新订单
     * @param express
     */
    void addNewExpressOrder(Express express) throws GatewayException;

    /**
     * 抢单
     * @param orderNumber
     */
    void catchOrder(String orderNumber) throws GatewayException;

    /**
     * 取消订单
     * @param orderNumber
     */
    void cancelOrder(String orderNumber);

    /**
     * 模拟支付
     * @param orderNumber
     */
    void payOrder(String orderNumber);

    /**
     * 确认送达
     * @param orderNumber
     */
    void sended(String orderNumber);

    /**
     * 确认收货
     * @param orderNumber
     */
    void received(String orderNumber);

    /**
     * 授权
     * @param orderNumber
     */
    void authorization(String orderNumber);

}
