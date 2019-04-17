package com.njit.xydl.life.common.feignservice;

import com.njit.xydl.life.common.feignservice.dto.PayDTO;
import com.njit.xydl.life.common.feignservice.hystrix.PayServiceHystrix;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
@FeignClient(name = "XYDL-USERS", fallback = PayServiceHystrix.class)
public interface PayService {

	@PostMapping("/pay/personToPerson")
	Response payPersonToPerson(@RequestBody PayDTO param);

	@PostMapping("/pay/personToTemporary")
	Response payPersonToTemporary(@RequestBody PayDTO param);

	@PostMapping("/pay/temporaryToPerson")
	Response payTemporaryToPerson(@RequestBody PayDTO param);

}
