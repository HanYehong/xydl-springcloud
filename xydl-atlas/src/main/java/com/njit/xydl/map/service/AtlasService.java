package com.njit.xydl.map.service;

import com.njit.xydl.map.entity.Atlas;
import com.njit.xydl.map.entity.AtlasExample;
import com.njit.xydl.map.entity.vo.AtlasVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AtlasService {

    List<AtlasVo> getAll();

    long countByExample(AtlasExample example);

    int deleteByExample(AtlasExample example);

    int deleteByPrimaryKey(String id);

    int insert(Atlas record);

    int insertSelective(Atlas record);

    List<Atlas> selectByExample(AtlasExample example);

    List<Atlas> selectAll();

    Atlas selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Atlas record, @Param("example") AtlasExample example);

    int updateByExample(@Param("record") Atlas record, @Param("example") AtlasExample example);

    int updateByPrimaryKeySelective(Atlas record);

    int updateByPrimaryKey(Atlas record);
}
