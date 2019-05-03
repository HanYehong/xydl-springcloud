package com.njit.xydl.life.express.controller;

import com.alibaba.fastjson.JSON;
import com.njit.xydl.life.common.enums.StatusEnum;
import com.njit.xydl.life.common.feign.UserService;
import com.njit.xydl.life.express.service.ExpressService;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private UserService userService;

    @GetMapping("/listUnAcceptOrder")
    public Response listUnAcceptOrder() {
        return Response.ok(expressService.listExpressOrderByStatus(StatusEnum.WAIT_ACCEPT.getCode()));
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
