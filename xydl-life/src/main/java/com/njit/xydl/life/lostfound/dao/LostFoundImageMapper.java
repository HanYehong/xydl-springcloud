package com.njit.xydl.life.lostfound.dao;

import com.njit.xydl.life.lostfound.entity.LostFoundImage;

public interface LostFoundImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LostFoundImage record);

    int insertSelective(LostFoundImage record);

    LostFoundImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LostFoundImage record);

    int updateByPrimaryKey(LostFoundImage record);
}