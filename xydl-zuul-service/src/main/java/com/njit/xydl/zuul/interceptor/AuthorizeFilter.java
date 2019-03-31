package com.njit.xydl.zuul.interceptor;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.exception.ValidException;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HanYehong
 * @date 2019/3/30 17:55
 */
public class AuthorizeFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(AuthorizeFilter.class);

    /**
     * 排除过滤的 uri 地址
     */
    private static final String LOGIN_URI = "/xydl-users/login/getToken";
    /**
     * 如果身份验证成功 则设置有效时间为2天
     */
    private static final Integer EXPIRE_TIME = 3600 * 48;
    /**
     * 身份验证关键字段
     */
    private static final String TOKEN = "token";

    @Override
    public Object run() throws GatewayException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        logger.info("[info] 请求url <<< " + request.getRequestURI());

        String token = request.getHeader(TOKEN);

        if (token == null || StringUtils.isBlank(token) || verifyToken(token)) {

            context.setSendZuulResponse(false);
            throw new GatewayException(Status.UN_AUTHORIZE);

//            unAuthorizedResponse(context);

        } else {

            logger.info("[info] 存在token <<< " + token);

            context.setSendZuulResponse(true);
            context.setResponseStatusCode(Status.OK.getCode());
        }

        return null;
    }

    private boolean verifyToken(String token) {

        String openId = RedisHelper.getRedisUtil().get(token);

        if (openId == null || StringUtils.isBlank(openId)) {
            return false;
        }

        RedisHelper.getRedisUtil().expire(token, EXPIRE_TIME);

        return true;
    }

    private void unAuthorizedResponse(RequestContext context) {

        logger.error("[error] 身份验证失败 <<<");

        context.setSendZuulResponse(false);
        context.setResponseStatusCode(Status.UN_AUTHORIZE.getCode());

        HttpServletResponse response = context.getResponse();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        context.setResponseBody(JSON.toJSONString(Response.ok(Status.UN_AUTHORIZE)));
    }

    @Override
    public String filterType() {

        return "pre";
    }

    @Override
    public int filterOrder() {

        return 0;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        return !LOGIN_URI.equals(request.getRequestURI());
    }

}