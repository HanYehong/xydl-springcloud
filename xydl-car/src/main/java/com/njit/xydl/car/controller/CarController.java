package com.njit.xydl.car.controller;

import com.njit.xydl.car.service.CarService;
import com.njit.xydl.car.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("/location")
    public JSONResult location(@RequestParam int number, @RequestParam double longitude, @RequestParam double laitude){
        try{
            carService.entryCarLocation(number,longitude,laitude);
            return JSONResult.ok();
        }catch (Exception e){
            return JSONResult.errorMsg(e.getMessage());
        }
    }

    @RequestMapping("/getOne")
    public JSONResult getLocation(@RequestParam int number){
        try {
            Map<String, Double> l = carService.getCarLocation(number);
            return JSONResult.ok(l);
        }catch (Exception e){
            return JSONResult.errorMsg(e.getMessage());
        }
    }

    @RequestMapping("/getAll")
    public JSONResult getAllLocation(){
        try {
            List<Map<String, Double>> list = carService.getAllCarLocation();
            return JSONResult.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg(e.getMessage());
        }
    }

}
