package com.njit.xydl.users.service.bo;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
public class SessionBO {

	private String session_key;

	private String openId;

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
