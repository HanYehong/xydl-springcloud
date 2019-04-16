package com.njit.xydl.life.express.service.bo;

import com.njit.xydl.life.common.entity.Express;

import java.util.List;

/**
 * @author yehong.han
 * @date 2019/4/16
 */
public class OrderListBO {
	/**
	 * 进行中
	 */
	private List<Express> processing;
	/**
	 * 已完成
	 */
	private List<Express> complete;
	/**
	 * 未完成
	 */
	private List<Express> uncomplete;

	public List<Express> getProcessing() {
		return processing;
	}

	public void setProcessing(List<Express> processing) {
		this.processing = processing;
	}

	public List<Express> getComplete() {
		return complete;
	}

	public void setComplete(List<Express> complete) {
		this.complete = complete;
	}

	public List<Express> getUncomplete() {
		return uncomplete;
	}

	public void setUncomplete(List<Express> uncomplete) {
		this.uncomplete = uncomplete;
	}
}
