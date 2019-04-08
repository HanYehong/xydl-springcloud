package com.njit.xydl.life.feedback.dao;

import com.njit.xydl.life.common.entity.Feedback;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
}