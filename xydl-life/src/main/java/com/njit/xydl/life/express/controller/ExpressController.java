package com.njit.xydl.life.express.controller;

import com.alibaba.fastjson.JSON;
import com.njit.xydl.life.common.entity.Express;
import com.njit.xydl.life.common.enums.StatusEnum;
import com.njit.xydl.life.common.feign.UserService;
import com.njit.xydl.life.express.controller.request.ExpressRequest;
import com.njit.xydl.life.express.service.ExpressService;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HanYehong
 * @date 2019/3/26 0:35
 */
@RestController
@RequestMapping("express")
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    @Autowired
    private UserService userService;

    @GetMapping("/listUnAcceptOrder")
    public Response listUnAcceptOrder() {
        return Response.ok(expressService.listExpressOrderByStatus(StatusEnum.WAIT_ACCEPT.getCode()));
    }

    @GetMapping("/listByPublishor")
    public Response listAllOrderByPublishor() {
        return Response.ok(expressService.listAllOrderByPublishor());
    }

    @GetMapping("/listByAcceptor")
    public Response listAllOrderByAcceptor() {
        return Response.ok(expressService.listAllOrderByAcceptor());
    }

    @PostMapping("/catch")
    public Response catchOrder(@RequestBody ExpressRequest params) {
        expressService.catchOrder(params.getOrderNumber());
        return Response.ok();
    }

    @PostMapping("/authorization")
    public Response authorization(@RequestBody ExpressRequest params) {
        expressService.authorization(params.getOrderNumber());
        return Response.ok();
    }

    @PostMapping("/refuseAccept")
    public Response refuseCurrentAccept(@RequestBody ExpressRequest params) throws Exception {
        expressService.refuseCurrentAccept(params.getOrderNumber());
        return Response.ok();
    }

    @PostMapping("/sended")
    public Response sended(@RequestBody ExpressRequest params) {
        expressService.sended(params.getOrderNumber());
        return Response.ok();
    }

    @PostMapping("/received")
    public Response received(@RequestBody ExpressRequest params) {
        expressService.received(params.getOrderNumber());
        return Response.ok();
    }

    @PostMapping("/cancel")
    public Response cancelOrder(@RequestBody ExpressRequest params) {
        expressService.cancelOrder(params.getOrderNumber());
        return Response.ok();
    }

    @PostMapping("/publish")
    public Response publishExpressOrder(@RequestBody Express param) {
        expressService.publishExpressOrder(param);
        return Response.ok();
    }

    @PostMapping("/rePublish")
    public Response rePublish(@RequestBody ExpressRequest params) {
        expressService.rePublish(params.getOrderNumber());
        return Response.ok();
    }

    @PostMapping("/test")
    public Response test() {
        return Response.ok();
    }

    @PostMapping("/test2")
    public Response test2() {
        Response response = userService.test();
        System.out.println(JSON.toJSONString(response));
        if (response.getCode() == 10000) {
            return response;
        }
        return Response.ok(Status.FAIL, "请求远程服务失败");
    }
}
