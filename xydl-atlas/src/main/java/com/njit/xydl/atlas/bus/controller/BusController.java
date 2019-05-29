package com.njit.xydl.atlas.bus.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.njit.xydl.atlas.bus.controller.request.PointerRequest;
import com.njit.xydl.atlas.bus.service.BusService;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author HanYehong
 * @date 2019/4/1 22:25
 */
@RestController
@RequestMapping("bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/uploadPointer")
    public Response uploadPointer(@RequestBody String param) {
        System.out.println("----param----" + param);
        JSONArray objArray = JSON.parseArray(param);
        for (Object o : objArray) {
            JSONObject obj = (JSONObject) o;
            PointerRequest pointerRequest = new PointerRequest();
            pointerRequest.setLatitude(((BigDecimal) obj.get("latitude")).doubleValue());
            pointerRequest.setLongitude(((BigDecimal) obj.get("longitude")).doubleValue());
            pointerRequest.setBusCode((Integer) obj.get("busCode"));
            busService.uploadPointer(pointerRequest);
        }
        return Response.ok();
    }

    @GetMapping("/listPointer")
    public Response getAllBusPointer() {
        return Response.ok(busService.getAllPointer());
    }

    @GetMapping("/getPointer")
    public Response getSpecialPointer(@RequestParam("busCode") Integer busCode) throws GatewayException {
        return Response.ok(busService.getSpecialPointer(busCode));
    }
}
