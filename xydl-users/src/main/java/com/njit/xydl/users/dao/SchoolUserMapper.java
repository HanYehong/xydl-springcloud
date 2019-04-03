package com.njit.xydl.users.dao;

import com.njit.xydl.users.entity.SchoolUser;
import org.apache.ibatis.annotations.Param;

public interface SchoolUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SchoolUser record);

    int insertSelective(SchoolUser record);

    SchoolUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SchoolUser record);

    int updateByPrimaryKey(SchoolUser record);

    SchoolUser selectByStudentId(@Param("studentId") String studentId);
}