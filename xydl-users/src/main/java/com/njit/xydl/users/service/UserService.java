package com.njit.xydl.users.service;

import com.njit.xydl.users.controller.dto.LoginDTO;
import com.njit.xydl.users.entity.SchoolUser;
import com.njit.xydl.users.entity.WechatUser;
import com.yehong.han.config.exception.GatewayException;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
public interface UserService {

	WechatUser getUser(String openId);

	int checkRealIdentity(String openId);

	SchoolUser authorize(LoginDTO param);

	Object isAuthorize();
}
