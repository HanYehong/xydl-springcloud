package com.njit.xydl.life.common.feign.hystrix;

import com.njit.xydl.life.common.feign.UserService;
import com.njit.xydl.life.common.feign.dto.OpenIdDTO;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
@Component
public class UserServiceHystrix implements UserService {

	@Override
	public Response checkRealIdentity(OpenIdDTO param) {
		return Response.ok(Status.FAIL);
	}

	@Override
	public Response test() {
		return Response.ok(Status.OK, "Hystrix");
	}
}
