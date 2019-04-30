package com.njit.xydl.life.lostfound.controller;

import com.njit.xydl.life.lostfound.controller.request.SearchRequest;
import com.njit.xydl.life.lostfound.service.LostFoundService;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return Response.ok(lostFoundService.listAllLostFoundSelective(param));
	}
}
