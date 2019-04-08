package com.njit.xydl.atlas.navigation.service;

import com.njit.xydl.atlas.navigation.entity.Atlas;
import com.njit.xydl.atlas.navigation.entity.AtlasImage;
import com.yehong.han.config.exception.GatewayException;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/4/6 15:41
 */
public interface AtlasService {
    /**
     * 根据指定的类别查询出所有的坐标点
     * @param categoryCode
     * @return
     */
    List<Atlas> listCoordinateByCategory(Integer categoryCode) throws GatewayException;

    /**
     * 查询指定场景的所有图片
     * @param atlasId
     * @return
     */
    List<AtlasImage> listImageByAtlasId(Long atlasId) throws GatewayException;
}
