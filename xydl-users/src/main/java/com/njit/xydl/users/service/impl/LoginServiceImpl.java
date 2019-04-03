package com.njit.xydl.users.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.njit.xydl.users.controller.dto.LoginDTO;
import com.njit.xydl.users.controller.dto.TokenDTO;
import com.njit.xydl.users.dao.SchoolUserMapper;
import com.njit.xydl.users.dao.WechatUserMapper;
import com.njit.xydl.users.entity.SchoolUser;
import com.njit.xydl.users.entity.WechatUser;
import com.njit.xydl.users.service.LoginService;
import com.njit.xydl.users.service.bo.SessionBO;
import com.njit.xydl.users.util.HttpUtil;
import com.njit.xydl.users.util.Md5Util;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.exception.ValidException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Value("${wx.appId}")
	private String appId;

	@Value("${wx.appSecret}")
	private String appSecret;

	@Value("${wx.grantType}")
	private String grantType;

	@Value("${wx.requestUrl}")
	private String requestUrl;

	@Autowired
	private WechatUserMapper wechatUserMapper;

	@Autowired
	private SchoolUserMapper schoolUserMapper;

	@Autowired
	private HttpServletRequest httpServletRequest;

	private static final Integer EXPIRE_TIME = 3600 * 48;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String login(TokenDTO param) throws IOException {

		logger.info("[info] 获得用户请求参数，进行登录 <<< " + param.toString());

		if (StringUtils.isBlank(param.getCode())) {
			throw new ValidException("code为空");
		}

		if (param.getWechatUser() == null) {
			throw new ValidException("获取不到当前用户");
		}

		SessionBO result = getOpenIdByCode(param.getCode());

		String token = generateToken(result.getSession_key(), result.getOpenId());

		WechatUser dbUser = wechatUserMapper.selectByOpenId(result.getOpenId());

		WechatUser newUser = param.getWechatUser();

		if (dbUser != null) {

			updateUserInfo(dbUser, newUser);

			int ret = wechatUserMapper.updateByPrimaryKeySelective(newUser);
			if (ret < 1) {
				logger.error("[error] 登录时更新用户信息失败 <<<");
			}

		} else {

			newUser.setOpenId(result.getOpenId());

			int ret = wechatUserMapper.insertSelective(newUser);
			if (ret < 1) {
				logger.error("[error] 插入新用户失败 <<<");
				return "";
			}
		}

		RedisHelper.getRedisUtil().set(token, result.getOpenId());
		RedisHelper.getRedisUtil().expire(token, EXPIRE_TIME);
		return token;
	}

	@Override
	public boolean authorize(LoginDTO param) throws GatewayException {
		if (param.getUsername() == null) {
			throw new GatewayException("用户名不能为空");
		}

		if (param.getPassword() == null) {
			throw new GatewayException("密码不能为空");
		}

		SchoolUser schoolUser = schoolUserMapper.selectByStudentId(param.getUsername());

		if (schoolUser == null) {
			throw new GatewayException("用户名不存在");
		}

		if (!param.getPassword().equals(schoolUser.getPassword())) {
			return false;
		}

		String openId = RedisHelper.getRedisUtil().get(httpServletRequest.getHeader("token"));

		WechatUser wechatUser = wechatUserMapper.selectByOpenId(openId);

		if (wechatUser != null) {
			wechatUser.setStudentId(param.getUsername());
			wechatUserMapper.updateByPrimaryKeySelective(wechatUser);
			return true;
		}

		return false;
	}

	private String generateToken(String session_key, String openId) throws IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMM-ddHH-mmss");

		String token = Md5Util.encrypt(session_key + "-" + openId + "-" + sdf.format(new Date()));

		return token;
	}

	private SessionBO getOpenIdByCode(String code) throws IOException {

		String url = this.requestUrl + "?appid=" + appId + "&secret=" +
				appSecret + "&js_code=" + code + "&grant_type=" + grantType;

		// 请求
		String data = HttpUtil.get(url);

		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> json = mapper.readValue(data, Map.class);

		SessionBO session = new SessionBO();
		session.setSession_key((String) json.get("session_key"));
		session.setOpenId((String) json.get("openid"));

		return session;
	}

	private void updateUserInfo(WechatUser dbUser, WechatUser newUser) {
		newUser.setAuthenticateTime(dbUser.getAuthenticateTime());
		newUser.setCreateTime(dbUser.getCreateTime());
		newUser.setId(dbUser.getId());
		newUser.setIsDelete(dbUser.getIsDelete());
		newUser.setOpenId(dbUser.getOpenId());
		newUser.setStudentId(dbUser.getStudentId());
	}
}
