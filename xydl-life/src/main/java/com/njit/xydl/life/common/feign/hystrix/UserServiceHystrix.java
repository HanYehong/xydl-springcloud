package com.njit.xydl.life.common.feign.hystrix;

import com.njit.xydl.life.common.entity.WechatUser;
import com.njit.xydl.life.common.feign.UserService;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.stereotype.Component;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
@Component
public class UserServiceHystrix implements UserService {
	@Override
	public WechatUser getUser(String openId) {
		return null;
	}

	@Override
	public Boolean checkRealIdentity(String openId) {
		return false;
	}

	@Override
	public Response test() {
		return Response.ok(Status.OK, "Hystrix");
	}
}
