package com.njit.xydl.users.dao;

import com.njit.xydl.users.entity.WechatUser;
import org.apache.ibatis.annotations.Param;

public interface WechatUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);

    WechatUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WechatUser record);

    int updateByPrimaryKey(WechatUser record);

    WechatUser selectByOpenId(@Param("openId") String openId);
}