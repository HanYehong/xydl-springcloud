package com.njit.xydl.atlas.navigation.dao;

import com.njit.xydl.atlas.navigation.entity.AtlasImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AtlasImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AtlasImage record);

    int insertSelective(AtlasImage record);

    AtlasImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AtlasImage record);

    int updateByPrimaryKey(AtlasImage record);

    List<AtlasImage> selectByAtlasId(@Param("atlasId") Long atlasId);
}