package com.njit.xydl.users.dao;

import com.njit.xydl.users.entity.UsersSc;
import com.njit.xydl.users.entity.UsersScExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersScMapper {
    long countByExample(UsersScExample example);

    int deleteByExample(UsersScExample example);

    int deleteByPrimaryKey(String id);

    int insert(UsersSc record);

    int insertSelective(UsersSc record);

    List<UsersSc> selectByExample(UsersScExample example);

    UsersSc selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UsersSc record, @Param("example") UsersScExample example);

    int updateByExample(@Param("record") UsersSc record, @Param("example") UsersScExample example);

    int updateByPrimaryKeySelective(UsersSc record);

    int updateByPrimaryKey(UsersSc record);
}