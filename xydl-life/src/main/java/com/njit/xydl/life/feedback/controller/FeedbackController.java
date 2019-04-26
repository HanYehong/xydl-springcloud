package com.njit.xydl.life.feedback.controller;

import com.njit.xydl.life.feedback.controller.request.FeedbackRequest;
import com.njit.xydl.life.feedback.service.FeedbackService;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author HanYehong
 * @date 2019/4/7 23:03
 */
@RestController
@RequestMapping("feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/commit")
	public Response commitFeedback(@RequestBody FeedbackRequest param){
		feedbackService.addFeedbackContent(param);
		return Response.ok();
	}
}
