package com.njit.xydl.users.service.impl;

import com.njit.xydl.users.dao.WechatUserMapper;
import com.njit.xydl.users.entity.WechatUser;
import com.njit.xydl.users.service.PayService;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void payPersonToPerson(String accountA, String accountB, Double money) throws GatewayException {
		payProcess(accountA, money);
		acceptProcess(accountB, money);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void payPersonToTemporary(String accountA, Double money) throws GatewayException {
		payProcess(accountA, money);
		temporaryProcess(money, 1);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void payTemporaryToPerson(String accountB, Double money) throws GatewayException {
		temporaryProcess(money, 0);
		acceptProcess(accountB, money);
	}

	private void payProcess(String account, double money) throws GatewayException {
		WechatUser userA = getUserInfo(account);
		double tempMoney = userA.getMoneyPackage() - money;
		if (tempMoney < 0) {
			throw new GatewayException("账户余额不足");
		}
		userA.setMoneyPackage(tempMoney);
		updateUser(userA);
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
			throw new GatewayException("中间账户异常，请稍后再试");
		}
	}

	private WechatUser getUserInfo(String openId) throws GatewayException {
		WechatUser user = wechatUserMapper.selectByOpenId(openId);
		if (user == null) {
			throw new GatewayException("无效付款");
		}
		return user;
	}

	private void updateUser(WechatUser user) throws GatewayException {
		int resultB = wechatUserMapper.updateByPrimaryKeySelective(user);
		if (resultB < 1) {
			throw new GatewayException("付款失败，请稍后再试");
		}
	}
}
