package com.njit.xydl.life.common.enums;

/**
 * @author yehong.han
 * @date 2019/3/25
 */
public enum StatusEnum {

	/**
	 * 刚发布时的订单状态
	 */
	WAIT_ACCEPT(0, "待接单"),
	/**
	 * 当该订单被接单时的状态
	 */
	WAIT_SEND(1, "待送达"),
	/**
	 * 当该订单被点击“已送达”后的状态
	 */
	WAIT_CONFIRM(2, "待确认"),
	/**
	 * 该订单被完成（终结状态）
	 */
	COMPLETE(3, "已完成"),
	/**
	 * 该订单未完成（终结状态，例如，接单者取消订单、发布者取消订单）
	 */
	UN_COMPLETE(4, "未完成"),
	/**
	 * 订单被接单后但还未授权
	 */
	WAIT_AUTHORIZATION(5, "待授权");

	private Integer code;
	private String name;

	StatusEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
