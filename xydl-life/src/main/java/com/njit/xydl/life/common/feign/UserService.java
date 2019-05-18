package com.njit.xydl.life.common.feign;

import com.njit.xydl.life.common.feign.dto.OpenIdDTO;
import com.njit.xydl.life.common.feign.hystrix.UserServiceHystrix;
import com.yehong.han.config.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yehong.han
 * @date 2019/4/18
 */
@FeignClient(name = "xydl-user", fallback = UserServiceHystrix.class)
public interface UserService {

	@PostMapping("/user/checkRealIdentity")
	Boolean checkRealIdentity(@RequestBody OpenIdDTO param);

	@PostMapping("/user/test")
	Response test();
}
