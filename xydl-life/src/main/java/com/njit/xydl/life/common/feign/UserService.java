package com.njit.xydl.life.common.feign;

import com.njit.xydl.life.common.entity.WechatUser;
import com.njit.xydl.life.common.feign.hystrix.UserServiceHystrix;
import com.yehong.han.config.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
@FeignClient(name = "xydl-users", fallback = UserServiceHystrix.class)
public interface UserService {

	@PostMapping("/user/getUser")
	WechatUser getUser(@RequestParam("openId") String openId);

	@PostMapping("/checkRealIdentity")
	Boolean checkRealIdentity(@RequestParam("openId") String openId);

	@PostMapping("/user/test")
	Response test();
}
