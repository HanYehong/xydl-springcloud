package com.njit.xydl.users.service.impl;

import com.njit.xydl.users.dao.WechatUserMapper;
import com.njit.xydl.users.entity.WechatUser;
import com.njit.xydl.users.service.PayService;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
@Service
public class PayServiceImpl implements PayService {

	@Autowired
	private WechatUserMapper wechatUserMapper;

	private static final int INSUFFICIENT = 2;

	private static final int SUCCESS = 1;

	private static final int FAIL = 0;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int payPersonToPerson(String accountA, String accountB, Double money) throws GatewayException {
		int result = payProcess(accountA, money);
		if (result == INSUFFICIENT) {
			return INSUFFICIENT;
		}
		acceptProcess(accountB, money);
		return SUCCESS;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int payPersonToTemporary(String accountA, Double money) throws GatewayException {
		int result = payProcess(accountA, money);
		if (result == INSUFFICIENT) {
			return INSUFFICIENT;
		}
		temporaryProcess(money, 1);
		return SUCCESS;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int payTemporaryToPerson(String accountB, Double money) throws GatewayException {
		temporaryProcess(money, 0);
		acceptProcess(accountB, money);
		return SUCCESS;
	}

	private int payProcess(String account, double money) throws GatewayException {
		WechatUser userA = getUserInfo(account);
		double tempMoney = userA.getMoneyPackage() - money;
		if (tempMoney < 0) {
			return INSUFFICIENT;
		}
		userA.setMoneyPackage(tempMoney);
		updateUser(userA);
		return SUCCESS;
	}

	private void acceptProcess(String account, double money) throws GatewayException {
		WechatUser userB = getUserInfo(account);
		userB.setMoneyPackage(userB.getMoneyPackage() + money);
		updateUser(userB);
	}

	private void temporaryProcess(double money, int type) throws GatewayException {
		try {
			double totalMoney = Double.parseDouble(RedisHelper.getRedisUtil().get("moneypackage.temporary"));
			if (type == 0) {
				totalMoney -= money;
			} else {
				totalMoney += money;
			}
			RedisHelper.getRedisUtil().set("moneypackage.temporary", String.valueOf(totalMoney));
		} catch (Exception e) {
			throw new ValidException("中间账户异常，请稍后再试");
		}
	}

	private WechatUser getUserInfo(String openId) throws GatewayException {
		WechatUser user = wechatUserMapper.selectByOpenId(openId);
		if (user == null) {
			throw new ValidException("无效付款");
		}
		return user;
	}

	private void updateUser(WechatUser user) throws GatewayException {
		int resultB = wechatUserMapper.updateByPrimaryKeySelective(user);
		if (resultB < 1) {
			throw new ValidException("付款失败，请稍后再试");
		}
	}
}
