package com.njit.xydl.atlas.navigation.service.impl;

import com.njit.xydl.atlas.navigation.dao.AtlasImageMapper;
import com.njit.xydl.atlas.navigation.dao.AtlasMapper;
import com.njit.xydl.atlas.navigation.entity.Atlas;
import com.njit.xydl.atlas.navigation.entity.AtlasImage;
import com.njit.xydl.atlas.navigation.service.AtlasService;
import com.yehong.han.config.exception.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/4/6 15:54
 */
@Service
public class AtlasServiceImpl implements AtlasService {

    @Autowired
    private AtlasMapper atlasMapper;

    @Autowired
    private AtlasImageMapper imageMapper;

    @Override
    public List<Atlas> listCoordinateByCategory(Integer categoryCode) throws GatewayException {
        if (categoryCode == null) {
            throw new GatewayException("查询类别不能为空");
        }
        return atlasMapper.selectByCategory(categoryCode);
    }

    @Override
    public List<AtlasImage> listImageByAtlasId(Long atlasId) throws GatewayException {
        if (atlasId == null) {
            throw new GatewayException("查询场景不能为空");
        }
        return imageMapper.selectByAtlasId(atlasId);
    }
}
