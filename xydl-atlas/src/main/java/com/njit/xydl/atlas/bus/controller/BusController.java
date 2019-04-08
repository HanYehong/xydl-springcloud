package com.njit.xydl.atlas.bus.controller;

import com.njit.xydl.atlas.bus.controller.request.PointerRequest;
import com.njit.xydl.atlas.bus.service.BusService;
import com.yehong.han.config.exception.GatewayException;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Response uploadPointer(@RequestBody PointerRequest param) throws GatewayException {
        busService.uploadPointer(param);
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
