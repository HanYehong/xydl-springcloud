package com.njit.xydl.life.enums;

/**
 * @author yehong.han
 * @date 2019/3/25
 */
public enum SizeEnum {

	/**
	 * 小型快递
	 */
	SMALL_SIZE(0, "小物"),
	/**
	 * 中等快递
	 */
	MIDDLE_SIZE(1, "中物"),
	/**
	 * 大型快递
	 */
	LARGE_SIZE(2, "大物");

	private Integer code;
	private String name;

	SizeEnum(Integer code, String name) {
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
