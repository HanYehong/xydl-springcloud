package com.njit.xydl.users.controller;

import com.alibaba.fastjson.JSON;
import com.njit.xydl.users.controller.dto.LoginDTO;
import com.njit.xydl.users.controller.dto.OpenIdDTO;
import com.njit.xydl.users.entity.WechatUser;
import com.njit.xydl.users.service.UserService;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.exception.ValidException;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/getUser")
	public WechatUser getUser(@RequestParam("openId") String openId) {
		return userService.getUser(openId);
	}

	@PostMapping("/checkRealIdentity")
	public Response checkRealIdentity(@RequestBody OpenIdDTO param) {
		System.out.println("param=====" + JSON.toJSONString(param));
		return Response.ok(userService.checkRealIdentity(param.getOpenId()));
	}

	@PostMapping("/authorize")
	public Response authorize(@RequestBody LoginDTO param) {
		return Response.ok(userService.authorize(param));
	}

	@GetMapping("/isAuthorize")
	public Response isAuthorize() {
		return Response.ok(userService.isAuthorize());
	}

	@PostMapping("/test")
	public Response test() {
		throw new ValidException("测试异常");
//		return Response.ok("test");
	}
}
