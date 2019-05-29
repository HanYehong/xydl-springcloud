package com.njit.xydl.life.lostfound.controller;

import com.njit.xydl.life.lostfound.controller.request.LostNumberRequest;
import com.njit.xydl.life.lostfound.controller.request.SearchRequest;
import com.njit.xydl.life.lostfound.dao.result.LostFoundBean;
import com.njit.xydl.life.lostfound.service.LostFoundService;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yehong.han
 * @date 2019/4/30
 */
@RestController
@RequestMapping("lostFound")
public class LostFoundController {
	@Autowired
	private LostFoundService lostFoundService;

	@PostMapping("/select")
	public Response listAllLostFoundSelective(@RequestBody SearchRequest param) {
		if (param.getLostLocation() == -1) {
			param.setLostLocation(null);
		}
		if (param.getLostType() == -1) {
			param.setLostType(null);
		}
		if ("".equals(param.getCreateTime())) {
			param.setCreateTime(null);
		}
		return Response.ok(lostFoundService.listAllLostFoundSelective(param));
	}

	@PostMapping("/publish")
	public Response publishLostFound(@Validated @RequestBody LostFoundBean param) {
		lostFoundService.publishLostFound(param);
		return Response.ok();
	}

	@PostMapping("/delete")
	public Response deleteLostFound(@Validated @RequestBody LostNumberRequest param) {
		lostFoundService.deleteLostFound(param.getLostNumber());
		return Response.ok();
	}

	@GetMapping("/listMine")
	public Response listLostFoundByPerson() {
		return Response.ok(lostFoundService.listLostFoundByPerson());
	}

	@PostMapping("/getLostFound")
	public Response getLostFoundByLostNumber(@Validated @RequestBody LostNumberRequest param) {
		return Response.ok(lostFoundService.getLostFound(param.getLostNumber()));
	}
}
