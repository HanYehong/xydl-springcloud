package com.njit.xydl.life.dao;

import com.njit.xydl.life.entity.ExpressOrder;
import com.njit.xydl.life.entity.ExpressOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressOrderMapper {
    long countByExample(ExpressOrderExample example);

    int deleteByExample(ExpressOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExpressOrder record);

    int insertSelective(ExpressOrder record);

    List<ExpressOrder> selectByExample(ExpressOrderExample example);

    ExpressOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExpressOrder record, @Param("example") ExpressOrderExample example);

    int updateByExample(@Param("record") ExpressOrder record, @Param("example") ExpressOrderExample example);

    int updateByPrimaryKeySelective(ExpressOrder record);

    int updateByPrimaryKey(ExpressOrder record);
}