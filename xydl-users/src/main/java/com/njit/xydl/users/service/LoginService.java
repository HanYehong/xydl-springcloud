package com.njit.xydl.users.service;

import com.njit.xydl.users.controller.request.LoginRequest;
import com.njit.xydl.users.service.bo.SessionBO;

import java.io.IOException;
import java.util.Map;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
public interface LoginService {

	/**
	 * 登陆接口 并返回token给客户端
	 * @param param
	 * @return
	 * @throws IOException
	 */
	String login(LoginRequest param) throws IOException;

}
