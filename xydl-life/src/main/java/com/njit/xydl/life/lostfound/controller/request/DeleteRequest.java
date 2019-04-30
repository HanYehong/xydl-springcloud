package com.njit.xydl.life.lostfound.controller.request;

import javax.validation.constraints.NotEmpty;

/**
 * @author yehong.han
 * @date 2019/4/30
 */
public class DeleteRequest {
	@NotEmpty(message = "失物号不能为空")
	private String lostNumber;

	public String getLostNumber() {
		return lostNumber;
	}

	public void setLostNumber(String lostNumber) {
		this.lostNumber = lostNumber;
	}
}
