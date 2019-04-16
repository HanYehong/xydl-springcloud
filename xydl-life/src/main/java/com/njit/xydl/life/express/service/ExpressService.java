package com.njit.xydl.life.express.service;

import com.njit.xydl.life.common.entity.Express;
import com.njit.xydl.life.express.service.bo.OrderListBO;
import com.yehong.han.config.exception.GatewayException;

import java.util.List;

/**
 * @author yehong.han
 */
public interface ExpressService {

    /**
     * <p>查找自己发布的指定状态的快递单，包括：待接单、进行中、未完成、已完成等</p>
     * <p>查找自己接受的指定状态的快递单，包括：待接单、进行中、未完成、已完成等</p>
     * <p>接单</p> 检查接单者是否已经进行实名认证，判断是否是自己的单子，加锁，判断该单子的状态是否允许被接单，接单成功，设置单子状态为待授权，发送被接单短信给发布者
     * <p>授权</p> 设置订单状态为待送达，并设置接单者
     * <p>拒绝其接单</p> 重置订单状态为待接单
     * <p>已送达</p> 接单者确认送达，设置订单状态为待确认，即待发布者确认收货
     * <p>确认收货</p> 发布者确认收货，设置订单状态为已完成，并从中间账户打款给接单者
     * <p>取消订单</p> 若此时订单状态为待授权或者待接单，设置订单为未完成状态，且取消后金额回流至发布者账户；若此时订单状态在进行中，判断当前取消人为发布者还是接单者，发布者：设置订单为未完成状态，并从中间账户打款给接单者；接单者：设置订单为未完成状态，并直接支付给发布者
     * <p>发布订单</p> 检查发布者是否已经进行实名认证，设置订单状态为待接单
     * <p>重新发布</p> 发布者重新付款，更新订单状态为待接单
     * <p>模拟支付接口</p> 需要给模拟的中间账户加锁，防止并发问题导致金额不正确
     */

    /**
     * 列出所有指定状态的快递单
     * @param status
     * @return
     */
    List<Express> listExpressOrderByStatus(Integer status);

    /**
     * <p>查找自己发布的指定状态的快递单，包括：待接单、进行中、未完成、已完成等</p>
     * @return
     */
    List<Express> listExpressOrderByPublishor() throws GatewayException;

    /**
     * 查询当前人发布的所有进行中的订单
     * @return
     */
    OrderListBO listDoingOrderByPublishor() throws GatewayException;

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
    void cancelOrder(String orderNumber) throws GatewayException;

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
