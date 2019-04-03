package com.njit.xydl.users.controller.dto;

import com.njit.xydl.users.entity.WechatUser;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
public class TokenDTO {

	private String code;

	private WechatUser wechatUser;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public WechatUser getWechatUser() {
		return wechatUser;
	}

	public void setWechatUser(WechatUser wechatUser) {
		this.wechatUser = wechatUser;
	}
}
