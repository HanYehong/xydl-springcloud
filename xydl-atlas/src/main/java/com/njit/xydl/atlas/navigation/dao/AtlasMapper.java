package com.njit.xydl.atlas.navigation.dao;

import com.njit.xydl.atlas.navigation.entity.Atlas;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AtlasMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Atlas record);

    int insertSelective(Atlas record);

    Atlas selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Atlas record);

    int updateByPrimaryKey(Atlas record);

    List<Atlas> selectByCategory(@Param("category") Integer category);
}