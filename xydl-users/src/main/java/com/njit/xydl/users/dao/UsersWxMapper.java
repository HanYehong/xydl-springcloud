package com.njit.xydl.users.dao;

import com.njit.xydl.users.entity.UsersWx;
import com.njit.xydl.users.entity.UsersWxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersWxMapper {
    long countByExample(UsersWxExample example);

    int deleteByExample(UsersWxExample example);

    int deleteByPrimaryKey(String id);

    int insert(UsersWx record);

    int insertSelective(UsersWx record);

    List<UsersWx> selectByExample(UsersWxExample example);

    UsersWx selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UsersWx record, @Param("example") UsersWxExample example);

    int updateByExample(@Param("record") UsersWx record, @Param("example") UsersWxExample example);

    int updateByPrimaryKeySelective(UsersWx record);

    int updateByPrimaryKey(UsersWx record);
}