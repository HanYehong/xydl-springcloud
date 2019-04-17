package com.njit.xydl.users.service;

import com.yehong.han.config.exception.GatewayException;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
public interface PayService {

	/**
	 * 支付 A账户 ---> B账户
	 * @return
	 */
	int payPersonToPerson(String accountA, String accountB, Double money) throws GatewayException;

	/**
	 * 支付 A账户 ---> 中间账户
	 * @return
	 */
	int payPersonToTemporary(String accountA, Double money) throws GatewayException;

	/**
	 * 支付 中间账户 ---> B账户
	 * @return
	 */
	int payTemporaryToPerson(String accountB, Double money) throws GatewayException;
}
