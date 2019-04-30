package com.njit.xydl.life.lostfound.dao;

import com.njit.xydl.life.lostfound.controller.request.SearchRequest;
import com.njit.xydl.life.lostfound.entity.LostFound;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LostFoundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LostFound record);

    int insertSelective(LostFound record);

    LostFound selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LostFound record);

    int updateByPrimaryKey(LostFound record);

    List<LostFound> selectSelective(SearchRequest param);

    LostFound selectByLostNumber(@Param("lostNumber") String lostNumber);
}