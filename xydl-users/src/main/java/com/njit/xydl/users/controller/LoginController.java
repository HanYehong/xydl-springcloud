package com.njit.xydl.users.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.util.StringUtil;
import com.njit.xydl.users.controller.request.LoginRequest;
import com.njit.xydl.users.entity.UsersWx;
import com.njit.xydl.users.entity.Vo.UsersWxVo;
import com.njit.xydl.users.exception.ValidException;
import com.njit.xydl.users.service.UsersWxService;
import com.njit.xydl.users.util.HttpUtil;
import com.njit.xydl.users.util.MD5Util;
import com.yehong.han.config.response.Response;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yehong.han
 */
@RestController
@RequestMapping("login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Value("${wx.grantType}")
    private String grantType;

    @Value("${wx.requestUrl}")
    private String requestUrl;

    private static HashMap<String, UsersWx> map = new HashMap<>();

    @PostMapping("/getSession")
    public Response getSession(@RequestBody LoginRequest param) {

        log.info("获得用户请求参数，进行登录<<<<<");
        log.info(param.toString());

        if (param == null) {
            throw new ValidException("请勿传空参");
        }

        if (StringUtils.isBlank(param.getCode())) {
            throw new ValidException("code为空");
        }

        if (param.getUsersWx() == null) {
            throw new ValidException("user为空");
        }

  //      Map<String, Object> requestResult = this.getSessionByCode(param.getCode());

        String session_key = "123-456";//(String) requestResult.get("session_key");
        String openId = "123-456"; //(String) requestResult.get("openid");

        SimpleDateFormat sdf = new SimpleDateFormat("yyMM-ddHH-mmss");

        String sessionId = MD5Util.encrypt(session_key + "-" + openId + "-" + sdf.format(new Date()));

        return Response.ok(sessionId);
    }

    private Map<String, Object> getSessionByCode(String code) {
        String url = this.requestUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type="
                + grantType;
        // 发送请求
        String data = HttpUtil.get(url);
        log.debug("请求地址：{}", url);
        log.debug("请求结果：{}", data);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = null;
        try {
            json = mapper.readValue(data, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 形如{"session_key":"6w7Br3JsRQzBiGZwvlZAiA==","openid":"oQO565cXXXXXEvc4Q_YChUE8PqB60Y"}的字符串
        return json;
    }
}
