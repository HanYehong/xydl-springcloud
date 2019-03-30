package com.njit.xydl.users.controller.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.response.Status;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author yehong.han
 * @date 2019/3/26
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(CheckTokenInterceptor.class);

	private static HashMap<String, String> map = new HashMap<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		PrintWriter out = null;

		logger.info("Request请求地址<<<<<<<<<" + request.getServletPath());

		//从请求头获取token
		String token = request.getHeader("token");

		//查询是否存在token
		String openid = map.get(token);

		if (StringUtils.isBlank(openid)) {

			out = response.getWriter();

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", Status.fail.getCode());
			jsonObject.put("msg", "获取不到用户的token");
			out.write(jsonObject.toJSONString());

			out.flush();
			out.close();

			return false;

		}

		logger.info("获取用户token成功");

		RedisHelper.getRedisUtil().expire(token, 3600*48);

//		redisdao.expireEndTime(token);

		return true;
	}
}
