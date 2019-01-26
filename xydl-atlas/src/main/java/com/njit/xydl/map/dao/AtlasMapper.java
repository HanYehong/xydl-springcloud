package com.njit.xydl.map.dao;

import java.util.List;

import com.njit.xydl.map.entity.Atlas;
import com.njit.xydl.map.entity.AtlasExample;
import org.apache.ibatis.annotations.Param;

public interface AtlasMapper {
    long countByExample(AtlasExample example);

    int deleteByExample(AtlasExample example);

    int deleteByPrimaryKey(String id);

    int insert(Atlas record);

    int insertSelective(Atlas record);

    List<Atlas> selectByExample(AtlasExample example);

    Atlas selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Atlas record, @Param("example") AtlasExample example);

    int updateByExample(@Param("record") Atlas record, @Param("example") AtlasExample example);

    int updateByPrimaryKeySelective(Atlas record);

    int updateByPrimaryKey(Atlas record);
}