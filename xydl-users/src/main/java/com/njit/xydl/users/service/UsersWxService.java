package com.njit.xydl.users.service;

import com.njit.xydl.users.entity.UsersWx;
import com.njit.xydl.users.entity.UsersWxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersWxService {

    void checkOpenId(String openId, UsersWx user) throws Exception;

    UsersWx selectByOpenId(String openId);

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
