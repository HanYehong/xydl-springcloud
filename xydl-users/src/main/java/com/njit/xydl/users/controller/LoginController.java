package com.njit.xydl.users.controller;

import com.njit.xydl.users.controller.request.LoginRequest;
import com.njit.xydl.users.service.LoginService;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author yehong.han
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/getToken")
    public Response getSession(@RequestBody LoginRequest param) throws IOException {
        return Response.ok(loginService.login(param));
    }

}
