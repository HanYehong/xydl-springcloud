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
     * <p>接单，检查接单者是否已经进行实名认证，判断是否是自己的单子，加锁，判断该单子的状态是否允许被接单，接单成功，设置单子状态为待授权，并设置接单者，发送被接单短信给发布者</p>
     * <p>授权，设置订单状态为待送达</p>
     * <p>拒绝其接单，重置订单状态为待接单，重置接单人</p>
     * <p>已送达，接单者确认送达，设置订单状态为待确认，即待发布者确认收货</p>
     * <p>确认收货，发布者确认收货，设置订单状态为已完成，并从中间账户打款给接单者</p>
     * <p>取消订单，若此时订单状态为待授权或者待接单，设置订单为未完成状态，且取消后金额回流至发布者账户；若此时订单状态在进行中，判断当前取消人为发布者还是接单者，发布者：设置订单为未完成状态，并从中间账户打款给接单者；接单者：设置订单为未完成状态，并直接支付给发布者</p>
     * <p>发布订单，检查发布者是否已经进行实名认证，设置订单状态为待接单</p>
     * <p>重新发布，发布者重新付款，更新订单状态为待接单</p>
     * <p>模拟支付接口，需要给模拟的中间账户加锁，防止并发问题导致金额不正确</p>
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
    OrderListBO listAllOrderByPublishor() throws GatewayException;

    /**
     * <p>查找自己接受的指定状态的快递单，包括：待接单、进行中、未完成、已完成等</p>
     * @return
     */
    OrderListBO listAllOrderByAcceptor() throws GatewayException;

    /**
     * <p>接单，检查接单者是否已经进行实名认证，判断是否是自己的单子，加锁，判断该单子的状态是否允许被接单，接单成功，设置单子状态为待授权，并设置接单者，发送被接单短信给发布者</p>
     * @param orderNumber
     */
    void catchOrder(String orderNumber) throws GatewayException;

    /**
     * <p>授权，设置订单状态为待送达</p>
     * @param orderNumber
     */
    void authorization(String orderNumber) throws GatewayException;

    /**
     * <p>拒绝其接单，重置订单状态为待接单，重置接单人</p>
     * @param orderNumber
     */
    void refuseCurrentAccept(String orderNumber) throws GatewayException;

    /**
     * <p>已送达，接单者确认送达，设置订单状态为待确认，并发短信通知发布者</p>
     * @param orderNumber
     */
    void sended(String orderNumber) throws GatewayException;

    /**
     * <p>确认收货，发布者确认收货，设置订单状态为已完成，并从中间账户打款给接单者</p>
     * @param orderNumber
     */
    void received(String orderNumber) throws GatewayException;

    /**
     * <p>取消订单，若此时订单状态为待授权或者待接单，设置订单为未完成状态，且取消后金额回流至发布者账户；若此时订单状态在进行中，判断当前取消人为发布者还是接单者，发布者：设置订单为未完成状态，并从中间账户打款给接单者；接单者：设置订单为未完成状态，并直接支付给发布者</p>
     * @param orderNumber
     */
    void cancelOrder(String orderNumber) throws GatewayException;

    /**
     * <p>发布订单，检查发布者是否已经进行实名认证，设置订单状态为待接单</p>
     * @param express
     */
    void publishExpressOrder(Express express) throws GatewayException;

    /**
     * <p>重新发布，发布者重新付款，更新订单状态为待接单</p>
     * @param orderNumber
     */
    void rePublish(String orderNumber) throws GatewayException;

    /**
     * 模拟支付
     * @param orderNumber
     */
    void pay(String orderNumber);

}
