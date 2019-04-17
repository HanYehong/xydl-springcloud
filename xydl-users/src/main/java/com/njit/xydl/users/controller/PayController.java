package com.njit.xydl.users.controller;

import com.njit.xydl.users.controller.dto.PayDTO;
import com.njit.xydl.users.service.PayService;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.response.Response;
import com.yehong.han.config.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yehong.han
 * @date 2019/4/17
 */
@RestController
@RequestMapping("pay")
public class PayController {

	@Autowired
	private PayService payService;

	@PostMapping("/personToPerson")
	public Response payPersonToPerson(@RequestBody PayDTO param) throws GatewayException {
		int result = payService.payPersonToPerson(param.getAccount(), param.getTargetAccount(), param.getMoney());
		if (result == Status.OK.getCode()) {
			return Response.ok();
		}
		return Response.fail();
	}

	@PostMapping("/personToTemporary")
	public Response payPersonToTemporary(@RequestBody PayDTO param) throws GatewayException {
		int result = payService.payPersonToTemporary(param.getAccount(), param.getMoney());
		if (result == Status.OK.getCode()) {
			return Response.ok();
		}
		return Response.fail();
	}

	@PostMapping("/temporaryToPerson")
	public Response payTemporaryToPerson(@RequestBody PayDTO param) throws GatewayException {
		int result = payService.payTemporaryToPerson(param.getTargetAccount(), param.getMoney());
		if (result == Status.OK.getCode()) {
			return Response.ok();
		}
		return Response.fail();
	}

}
