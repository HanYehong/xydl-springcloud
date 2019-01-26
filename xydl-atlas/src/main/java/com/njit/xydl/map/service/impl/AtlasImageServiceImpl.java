package com.njit.xydl.map.service.impl;

import com.njit.xydl.map.dao.AtlasImageMapper;
import com.njit.xydl.map.entity.AtlasImage;
import com.njit.xydl.map.entity.AtlasImageExample;
import com.njit.xydl.map.service.AtlasImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtlasImageServiceImpl implements AtlasImageService {

    @Autowired
    private AtlasImageMapper atlasImageMapper;

    @Override
    public long countByExample(AtlasImageExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AtlasImageExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(AtlasImage record) {
        return 0;
    }

    @Override
    public int insertSelective(AtlasImage record) {
        return 0;
    }

    @Override
    public List<AtlasImage> selectByExample(AtlasImageExample example) {
        return atlasImageMapper.selectByExample(example);
    }

    @Override
    public AtlasImage selectByPrimaryKey(Integer id) {
        return atlasImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(AtlasImage record, AtlasImageExample example) {
        return 0;
    }

    @Override
    public int updateByExample(AtlasImage record, AtlasImageExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(AtlasImage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AtlasImage record) {
        return 0;
    }
}
