package com.njit.xydl.life.dao;

import com.njit.xydl.life.entity.Express;

import java.util.List;

public interface ExpressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Express record);

    int insertSelective(Express record);

    Express selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);

    List<Express> selectAllUnAccept();
}