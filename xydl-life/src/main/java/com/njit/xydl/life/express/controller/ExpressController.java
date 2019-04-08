package com.njit.xydl.life.express.controller;

import com.njit.xydl.life.common.enums.StatusEnum;
import com.njit.xydl.life.express.service.ExpressService;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HanYehong
 * @date 2019/3/26 0:35
 */
@RestController
@RequestMapping("express")
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    @PostMapping("/listUnAcceptOrder")
    public Response listUnAcceptOrder() {
        return Response.ok(expressService.listExpressOrderByStatus(StatusEnum.WAIT_ACCEPT.getCode()));
    }

    @PostMapping("/listCompleteOrder")
    public Response listCompleteOrder() throws GatewayException {
        return Response.ok(
                expressService.listExpressOrderByStatusAndPublishor(StatusEnum.COMPLETE.getCode()));
    }

    @PostMapping("/listUnCompleteOrder")
    public Response listUnCompleteOrder() throws GatewayException {
        return Response.ok(
                expressService.listExpressOrderByStatusAndPublishor(StatusEnum.UN_COMPLETE.getCode()));
    }

    @PostMapping("/listDoingOrder")
    public Response listDoingOrder() throws GatewayException {
        return Response.ok(expressService.listDoingOrderByPublishor());
    }

    @PostMapping("/test")
    public Response test() {
        return Response.ok();
    }
}
