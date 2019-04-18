package com.njit.xydl.users.service.impl;

import com.njit.xydl.users.dao.WechatUserMapper;
import com.njit.xydl.users.entity.WechatUser;
import com.njit.xydl.users.service.UserService;
import com.yehong.han.config.response.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private WechatUserMapper wechatUserMapper;

	@Override
	public WechatUser getUser(String openId) {
		return wechatUserMapper.selectByOpenId(openId);
	}

	@Override
	public boolean checkRealIdentity(String openId) {
		WechatUser user = getUser(openId);
		return user != null && !StringUtils.isBlank(user.getStudentId()) && user.getAuthenticateTime() != null;
	}
}
