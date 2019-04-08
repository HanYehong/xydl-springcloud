package com.njit.xydl.users.service;

import com.njit.xydl.users.controller.dto.LoginDTO;
import com.njit.xydl.users.controller.dto.TokenDTO;
import com.yehong.han.config.exception.GatewayException;

import java.io.IOException;

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
	String login(TokenDTO param) throws IOException;

	boolean authorize(LoginDTO param) throws GatewayException;

}
