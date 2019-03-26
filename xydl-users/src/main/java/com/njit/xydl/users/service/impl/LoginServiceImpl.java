package com.njit.xydl.users.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.njit.xydl.users.controller.request.LoginRequest;
import com.njit.xydl.users.exception.ValidException;
import com.njit.xydl.users.service.LoginService;
import com.njit.xydl.users.service.bo.SessionBO;
import com.njit.xydl.users.util.HttpUtil;
import com.njit.xydl.users.util.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

	@Override
	public String login(LoginRequest param) throws IOException {

		logger.info("获得用户请求参数，进行登录<<<<<");
		logger.info(param.toString());

		if (param == null) {
			throw new ValidException("请勿传空参");
		}

		if (StringUtils.isBlank(param.getCode())) {
			throw new ValidException("code为空");
		}

		if (param.getUsersWx() == null) {
			throw new ValidException("user为空");
		}

		SessionBO result = getOpenIdByCode(param.getCode());

		SimpleDateFormat sdf = new SimpleDateFormat("yyMM-ddHH-mmss");

		String token = Md5Util.encrypt(result.getSession_key() + "-" + result.getOpenId() + "-" + sdf.format(new Date()));

		return token;
	}

	public SessionBO getOpenIdByCode(String code) throws IOException {

		String url = this.requestUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type="
				+ grantType;

		// 请求
		String data = HttpUtil.get(url);

		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> json = mapper.readValue(data, Map.class);

		SessionBO session = new SessionBO();
		session.setSession_key((String) json.get("session_key"));
		session.setOpenId((String) json.get("openid"));

		return session;
	}
}
