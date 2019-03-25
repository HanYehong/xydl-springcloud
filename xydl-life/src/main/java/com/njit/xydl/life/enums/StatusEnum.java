package com.njit.xydl.life.enums;

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
	 * 当该订单被确认收货后但还没付款时的状态
	 */
	WAIT_PUBLISHOR_PAY(3, "待发布者付款"),
	/**
	 * 当该订单被接单者取消但是还没付款时的状态
	 */
	WAIT_ACCEPTOR_PAY(3, "待接单者付款"),
	/**
	 * 该订单被完成（终结状态）
	 */
	COMPLETE(4, "已完成"),
	/**
	 * 该订单未完成（终结状态，例如，接单者取消订单、发布者取消订单）
	 */
	UN_COMPLETE(5, "未完成");

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
