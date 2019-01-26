package com.njit.xydl.map.controller;

import com.njit.xydl.map.entity.Atlas;
import com.njit.xydl.map.entity.vo.AtlasVo;
import com.njit.xydl.map.entity.vo.CarMarkersVo;
import com.njit.xydl.map.service.AtlasService;
import com.njit.xydl.map.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class AtlasController {

    @Autowired
    private AtlasService atlasService;

    private double longitude[] = {118.884875,118.884414,118.884189,118.883899,118.883159};
    private double latitude[] = {31.922585,31.922594,31.922558,31.922426,31.922148};
    private int count = 0;

    @GetMapping("/find/{mapID}")
    public JSONResult selectByKey(@PathVariable("mapID") String id){
   //     System.out.println("hi");
        return JSONResult.ok(atlasService.selectByPrimaryKey(id));
    }

    @GetMapping("/carMarkers")
    public JSONResult getCarMarkers(){
        CarMarkersVo carMarkersVo = new CarMarkersVo();
        HashMap<String, Double> markers[] = new HashMap[5];
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("latitude",latitude[count]);
        hashMap.put("longitude",longitude[count]);
        count = (count + 1)%5;
        return JSONResult.ok(hashMap);
    }

    @GetMapping("/test")
    public JSONResult test(){
        return JSONResult.ok("test");
    }

    @GetMapping("/getAll")
    public JSONResult selectAllAtlas(){
        try {
            List<AtlasVo> list = atlasService.getAll();
            return JSONResult.ok(list);
        }catch (Exception e){
            return JSONResult.errorMap(e);
        }
    }
}
