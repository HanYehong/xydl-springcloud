package com.njit.xydl.life.feedback.service;

import com.njit.xydl.life.common.entity.Feedback;
import com.njit.xydl.life.feedback.controller.request.FeedbackRequest;
import com.yehong.han.config.exception.GatewayException;

/**
 * @author HanYehong
 * @date 2019/4/7 23:04
 */
public interface FeedbackService {

	void addFeedbackContent(FeedbackRequest param);
}
