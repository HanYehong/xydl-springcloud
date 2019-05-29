package com.njit.xydl.life.common.feign;

import com.njit.xydl.life.common.feign.dto.PayDTO;
import com.njit.xydl.life.common.feign.hystrix.PayServiceHystrix;
import com.yehong.han.config.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
@FeignClient(name = "xydl-user", fallback = PayServiceHystrix.class)
public interface PayService {

	@PostMapping("/pay/personToPerson")
	Integer payPersonToPerson(@RequestBody PayDTO param);

	@PostMapping("/pay/personToTemporary")
	Integer payPersonToTemporary(@RequestBody PayDTO param);

	@PostMapping("/pay/temporaryToPerson")
	Integer payTemporaryToPerson(@RequestBody PayDTO param);

}
