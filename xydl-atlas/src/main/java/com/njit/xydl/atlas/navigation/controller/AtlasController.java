package com.njit.xydl.atlas.navigation.controller;

import com.njit.xydl.atlas.navigation.service.AtlasService;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HanYehong
 * @date 2019/4/6 16:00
 */
@RestController
@RequestMapping("atlas")
public class AtlasController {

    @Autowired
    private AtlasService atlasService;

    @PostMapping("/getCoordinate")
    public Response listCoordinateByCategory(Integer category) throws GatewayException {
        return Response.ok(atlasService.listCoordinateByCategory(category));
    }

    @PostMapping("/getImage")
    public Response listImage(Long atlasId) throws GatewayException {
        return Response.ok(atlasService.listImageByAtlasId(atlasId));
    }
}
