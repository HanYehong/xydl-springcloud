package com.njit.xydl.users.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njit.xydl.users.entity.Ao.CodeAndInfo;
import com.njit.xydl.users.entity.UsersWx;
import com.njit.xydl.users.entity.Vo.UsersWxVo;
import com.njit.xydl.users.service.UsersWxService;
import com.njit.xydl.users.utils.HttpUtil;
import com.njit.xydl.users.utils.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserWxController {

    private static Logger log = LoggerFactory.getLogger(UserWxController.class);

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Value("${wx.grantType}")
    private String grantType;

    @Value("${wx.requestUrl}")
    private String requestUrl;

    @Autowired
    private UsersWxService usersWxService;

    @ResponseBody
    @PostMapping("/openid")
    public JSONResult getSession(@RequestBody(required = false) String params) {
        try{
            //fastjson将JSON字符串转为JSON对象
            /**
             * 字符串形式如同 {"code":"023amjcl08n2Cr1sY2al0l5Tbl0amjcS","usersWx":{……}}
             * 其中usersWx字段为UsersWx对象字段
             */
            JSONObject object = JSONObject.parseObject(params);
            //将usersWx字符串转为UsersWx对象
            UsersWx usersWx = (UsersWx)JSONObject.toJavaObject(object.getJSONObject("usersWx"),UsersWx.class);
            //通过code获得openid
            Map<String, Object> map = this.getSessionByCode(object.getString("code"));
            //包装成即将返回的VO对象，返回的信息包括openid和session_key
            UsersWxVo usersWxVo = new UsersWxVo((String)map.get("session_key"),(String) map.get("openid"));
            //检查openid是否已经存在数据库中，存在则检查是否更新，不存在直接插入数据，数据为usersWx内的信息
            usersWxService.checkOpenId((String) map.get("openid"),usersWx);
            return JSONResult.ok(usersWxVo);
        }catch (Exception e){
            return JSONResult.errorMsg(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
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
