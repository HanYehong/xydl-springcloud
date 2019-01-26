package com.njit.xydl.map.service.impl;

import com.njit.xydl.map.dao.AtlasMapper;
import com.njit.xydl.map.entity.Atlas;
import com.njit.xydl.map.entity.AtlasExample;
import com.njit.xydl.map.entity.AtlasImage;
import com.njit.xydl.map.entity.AtlasImageExample;
import com.njit.xydl.map.entity.vo.AtlasVo;
import com.njit.xydl.map.service.AtlasImageService;
import com.njit.xydl.map.service.AtlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AtlasServiceImpl implements AtlasService {

    @Autowired
    private AtlasMapper atlasMapper;

    @Autowired
    private AtlasImageService atlasImageService;

    @Override
    public List<AtlasVo> getAll() {
        List<AtlasVo> list = new ArrayList<>();
        List<Atlas> atlases = atlasMapper.selectByExample(null); //搜索全部
        for(Iterator<Atlas> iterator = atlases.iterator(); iterator.hasNext(); ){
            //搜索该地点的所有场景图
            AtlasImageExample example = new AtlasImageExample();
            example.createCriteria().andAtlasIdEqualTo(iterator.next().getId());
            List<AtlasImage> atlasImages = atlasImageService.selectByExample(example);
            //将场景图的imageUrl取出，添加到数组中
            List<String> imageUrl = new ArrayList<>();
            for(Iterator<AtlasImage> iterator2 = atlasImages.iterator(); iterator2.hasNext();){
                imageUrl.add(iterator2.next().getImageUrl());
            }
            //封装VO对象
            AtlasVo atlasVo = new AtlasVo(iterator.next());
            atlasVo.setImageUrl(imageUrl);
            list.add(atlasVo);
        }
        return list;
    }

    @Override
    public long countByExample(AtlasExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(AtlasExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Atlas record) {
        return 0;
    }

    @Override
    public int insertSelective(Atlas record) {
        return 0;
    }

    @Override
    public List<Atlas> selectByExample(AtlasExample example) {
        return null;
    }

    @Override
    public List<Atlas> selectAll() {
        return atlasMapper.selectByExample(null);
    }

    @Override
    public Atlas selectByPrimaryKey(String id) {
        return atlasMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Atlas record, AtlasExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Atlas record, AtlasExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Atlas record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Atlas record) {
        return 0;
    }
}
