package com.njit.xydl.users.service;

import com.njit.xydl.users.entity.WechatUser;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
public interface UserService {

	WechatUser getUser(String openId);

	boolean checkRealIdentity(String openId);
}
