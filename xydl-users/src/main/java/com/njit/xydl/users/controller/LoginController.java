package com.njit.xydl.users.controller;

import com.njit.xydl.users.controller.dto.LoginDTO;
import com.njit.xydl.users.controller.dto.TokenDTO;
import com.njit.xydl.users.service.LoginService;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author yehong.han
 */
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/getToken")
    public Response getSession(@RequestBody TokenDTO param) throws IOException {
        return Response.ok(loginService.login(param));
    }

    @PostMapping("/authorize")
    public Response authorize(@RequestBody LoginDTO param) throws GatewayException {
        return Response.ok(loginService.authorize(param));
    }

}
