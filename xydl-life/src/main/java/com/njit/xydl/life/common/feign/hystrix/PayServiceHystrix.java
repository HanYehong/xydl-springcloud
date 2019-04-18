package com.njit.xydl.life.common.feign.hystrix;

import com.njit.xydl.life.common.feign.PayService;
import com.njit.xydl.life.common.feign.dto.PayDTO;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.stereotype.Component;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
@Component
public class PayServiceHystrix implements PayService {
	@Override
	public Integer payPersonToPerson(PayDTO param) {
		return 0;
	}

	@Override
	public Integer payPersonToTemporary(PayDTO param) {
		return 0;
	}

	@Override
	public Integer payTemporaryToPerson(PayDTO param) {
		return 0;
	}
}
