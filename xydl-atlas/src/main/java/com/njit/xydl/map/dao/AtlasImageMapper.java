package com.njit.xydl.map.dao;

import java.util.List;

import com.njit.xydl.map.entity.AtlasImage;
import com.njit.xydl.map.entity.AtlasImageExample;
import org.apache.ibatis.annotations.Param;

public interface AtlasImageMapper {
    long countByExample(AtlasImageExample example);

    int deleteByExample(AtlasImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AtlasImage record);

    int insertSelective(AtlasImage record);

    List<AtlasImage> selectByExample(AtlasImageExample example);

    AtlasImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AtlasImage record, @Param("example") AtlasImageExample example);

    int updateByExample(@Param("record") AtlasImage record, @Param("example") AtlasImageExample example);

    int updateByPrimaryKeySelective(AtlasImage record);

    int updateByPrimaryKey(AtlasImage record);
}