package com.njit.xydl.life.common.feignservice.hystrix;

import com.njit.xydl.life.common.feignservice.PayService;
import com.njit.xydl.life.common.feignservice.dto.PayDTO;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
public class PayServiceHystrix implements PayService {
	@Override
	public Response payPersonToPerson(PayDTO param) {
		return Response.ok(Status.FAIL, "服务繁忙，请稍后再试");
	}

	@Override
	public Response payPersonToTemporary(PayDTO param) {
		return Response.ok(Status.FAIL, "服务繁忙，请稍后再试");
	}

	@Override
	public Response payTemporaryToPerson(PayDTO param) {
		return Response.ok(Status.FAIL, "服务繁忙，请稍后再试");
	}
}
