package com.njit.xydl.users.dao;

import com.njit.xydl.users.entity.WxSc;
import com.njit.xydl.users.entity.WxScExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxScMapper {
    long countByExample(WxScExample example);

    int deleteByExample(WxScExample example);

    int deleteByPrimaryKey(String id);

    int insert(WxSc record);

    int insertSelective(WxSc record);

    List<WxSc> selectByExample(WxScExample example);

    WxSc selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WxSc record, @Param("example") WxScExample example);

    int updateByExample(@Param("record") WxSc record, @Param("example") WxScExample example);

    int updateByPrimaryKeySelective(WxSc record);

    int updateByPrimaryKey(WxSc record);
}