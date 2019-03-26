package com.njit.xydl.life.controller;

import com.njit.xydl.life.enums.StatusEnum;
import com.njit.xydl.life.service.ExpressService;
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
    public Response listCompleteOrder(String openId) {
        return Response.ok(expressService.listExpressOrderByStatus(StatusEnum.COMPLETE.getCode()));
    }

    @PostMapping("/test")
    public Response test() {
        return Response.ok();
    }
}
