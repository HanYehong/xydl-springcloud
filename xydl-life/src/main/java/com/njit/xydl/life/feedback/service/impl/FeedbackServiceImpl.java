package com.njit.xydl.life.feedback.service.impl;

import com.njit.xydl.life.common.entity.Feedback;
import com.njit.xydl.life.feedback.controller.request.FeedbackRequest;
import com.njit.xydl.life.feedback.dao.FeedbackMapper;
import com.njit.xydl.life.feedback.service.FeedbackService;
import com.yehong.han.config.authorization.UserUtil;
import com.yehong.han.config.exception.ValidException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yehong.han
 * @date 2019/4/26
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	public void addFeedbackContent(FeedbackRequest param) {
		if (StringUtils.isBlank(param.getContent())) {
			throw new ValidException("有什么想说的就告诉我们吧~");
		}
		Feedback feedback = new Feedback();
		feedback.setContent(param.getContent());
		feedback.setContactWay(param.getContactWay());
		feedback.setCommitor(UserUtil.getCurrentUserId());
		int result = feedbackMapper.insertSelective(feedback);
		if (result < 1) {
			throw new ValidException("服务繁忙，请稍后再试~");
		}
	}
}
