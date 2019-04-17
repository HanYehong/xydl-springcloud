package com.njit.xydl.life.common.feignservice.dto;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
public class PayDTO {

	private String account;

	private String targetAccount;

	private Double money;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
}
