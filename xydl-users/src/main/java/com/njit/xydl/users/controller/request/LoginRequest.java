package com.njit.xydl.users.controller.request;

import com.njit.xydl.users.entity.UsersWx;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
public class LoginRequest {

	private String code;

	private UsersWx usersWx;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UsersWx getUsersWx() {
		return usersWx;
	}

	public void setUsersWx(UsersWx usersWx) {
		this.usersWx = usersWx;
	}

	@Override
	public String toString() {
		return "LoginRequest{" +
				"code='" + code + '\'' +
				", usersWx=" + usersWx +
				'}';
	}
}
