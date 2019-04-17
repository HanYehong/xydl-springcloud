package com.njit.xydl.life.common.util;

import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HanYehong
 * @date 2019/4/7 23:22
 */
public class UserUtil {

    public static String getCurrentUserId() throws GatewayException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String openId = RedisHelper.getRedisUtil().get(request.getHeader("token"));
        if (StringUtils.isBlank(openId)) {
            throw new GatewayException("获取不到用户token");
        }
        return openId;
    }

}
