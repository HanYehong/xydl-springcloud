package com.njit.xydl.life.express.dao;

import com.njit.xydl.life.common.entity.Express;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Express record);

    int insertSelective(Express record);

    Express selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Express record);

    int updateByPrimaryKey(Express record);

    List<Express> selectExpressOrderByStatus(@Param("status") int status);

    List<Express> selectExpressOrderByStatusAndPublishor(@Param("status") int status,
                                                         @Param("publishor") String publishor);
}