package com.njit.xydl.life.dao;

import com.njit.xydl.life.entity.ExpressOrderStatus;
import com.njit.xydl.life.entity.ExpressOrderStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpressOrderStatusMapper {
    long countByExample(ExpressOrderStatusExample example);

    int deleteByExample(ExpressOrderStatusExample example);

    int deleteByPrimaryKey(String id);

    int insert(ExpressOrderStatus record);

    int insertSelective(ExpressOrderStatus record);

    List<ExpressOrderStatus> selectByExample(ExpressOrderStatusExample example);

    ExpressOrderStatus selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExpressOrderStatus record, @Param("example") ExpressOrderStatusExample example);

    int updateByExample(@Param("record") ExpressOrderStatus record, @Param("example") ExpressOrderStatusExample example);

    int updateByPrimaryKeySelective(ExpressOrderStatus record);

    int updateByPrimaryKey(ExpressOrderStatus record);
}