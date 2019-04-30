package com.njit.xydl.life.lostfound.controller.request;

import java.util.Date;

/**
 * @author yehong.han
 * @date 2019/4/30
 */
public class SearchRequest {
	private Integer lostType;

	private Integer lostLocation;

	private String createTime;

	public Integer getLostType() {
		return lostType;
	}

	public void setLostType(Integer lostType) {
		this.lostType = lostType;
	}

	public Integer getLostLocation() {
		return lostLocation;
	}

	public void setLostLocation(Integer lostLocation) {
		this.lostLocation = lostLocation;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
