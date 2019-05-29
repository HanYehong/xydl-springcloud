package com.njit.xydl.users.service.impl;

import com.njit.xydl.users.controller.dto.LoginDTO;
import com.njit.xydl.users.dao.SchoolUserMapper;
import com.njit.xydl.users.dao.WechatUserMapper;
import com.njit.xydl.users.entity.SchoolUser;
import com.njit.xydl.users.entity.WechatUser;
import com.njit.xydl.users.service.UserService;
import com.yehong.han.config.authorization.UserUtil;
import com.yehong.han.config.exception.ValidException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private WechatUserMapper wechatUserMapper;

	@Autowired
	private SchoolUserMapper schoolUserMapper;

	@Override
	public WechatUser getUser(String openId) {
		return wechatUserMapper.selectByOpenId(openId);
	}

	@Override
	public int checkRealIdentity(String openId) {
		WechatUser user = getUser(openId);
		if (user != null && !StringUtils.isBlank(user.getStudentId())) {
			return 1;
		}
		return 0;
	}

	@Override
	public SchoolUser authorize(LoginDTO param) {
		SchoolUser schoolUser = schoolUserMapper.selectByStudentId(param.getUsername());
		if (schoolUser == null) {
			throw new ValidException("用户名不存在");
		}
		if (!param.getPassword().equals(schoolUser.getPassword())) {
			throw new ValidException("认证失败");
		}
		WechatUser wechatUser = getUser();
		wechatUser.setStudentId(param.getUsername());
		wechatUserMapper.updateByPrimaryKeySelective(wechatUser);
		return schoolUser;
	}

	@Override
	public Object isAuthorize() {
		WechatUser wechatUser = getUser();
		if (wechatUser.getAuthenticateTime() != null && !StringUtils.isBlank(wechatUser.getStudentId())) {
			SchoolUser schoolUser = schoolUserMapper.selectByStudentId(wechatUser.getStudentId());
			if (schoolUser != null) {
				return schoolUser;
			}
			return 0;
		}
		return 0;
	}

	private WechatUser getUser() {
		WechatUser wechatUser = wechatUserMapper.selectByOpenId(UserUtil.getCurrentUserId());
		if (wechatUser == null) {
			throw new ValidException("获取不到用户信息");
		}
		return wechatUser;
	}
}
