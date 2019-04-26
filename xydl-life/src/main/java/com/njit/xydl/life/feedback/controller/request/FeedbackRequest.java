package com.njit.xydl.life.feedback.controller.request;

import javax.validation.constraints.NotNull;

/**
 * @author yehong.han
 * @date 2019/4/26
 */
public class FeedbackRequest {
	private String content;

	private String contactWay;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
}
