package com.njit.xydl.users.service.impl;

import com.njit.xydl.users.controller.dto.LoginDTO;
import com.njit.xydl.users.dao.SchoolUserMapper;
import com.njit.xydl.users.dao.WechatUserMapper;
import com.njit.xydl.users.entity.SchoolUser;
import com.njit.xydl.users.entity.WechatUser;
import com.njit.xydl.users.service.UserService;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.ValidException;
import com.yehong.han.config.response.Response;
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

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Override
	public WechatUser getUser(String openId) {
		return wechatUserMapper.selectByOpenId(openId);
	}

	@Override
	public boolean checkRealIdentity(String openId) {
		WechatUser user = getUser(openId);
		return user != null && !StringUtils.isBlank(user.getStudentId()) && user.getAuthenticateTime() != null;
	}

	@Override
	public SchoolUser authorize(LoginDTO param) {
		if (param.getUsername() == null) {
			throw new ValidException("用户名不能为空");
		}
		if (param.getPassword() == null) {
			throw new ValidException("密码不能为空");
		}
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
		}
		return 0;
	}

	private WechatUser getUser() {
//		String openId = RedisHelper.getRedisUtil().get(httpServletRequest.getHeader("token"));
		String openId = httpServletRequest.getHeader("token");
		System.out.println(openId);
		WechatUser wechatUser = wechatUserMapper.selectByOpenId(openId);
		if (wechatUser == null) {
			throw new ValidException("获取不到用户信息");
		}
		return wechatUser;
	}
}
