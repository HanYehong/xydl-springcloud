package com.njit.xydl.atlas.navigation.dao;

import com.njit.xydl.atlas.navigation.entity.AtlasImage;

public interface AtlasImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AtlasImage record);

    int insertSelective(AtlasImage record);

    AtlasImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AtlasImage record);

    int updateByPrimaryKey(AtlasImage record);
}