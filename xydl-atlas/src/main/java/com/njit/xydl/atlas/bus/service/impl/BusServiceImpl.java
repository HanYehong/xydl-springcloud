package com.njit.xydl.atlas.bus.service.impl;

import com.njit.xydl.atlas.bus.controller.request.PointerRequest;
import com.njit.xydl.atlas.bus.service.BusService;
import com.njit.xydl.atlas.common.cache.CacheKey;
import com.njit.xydl.atlas.common.enums.BusEnum;
import com.yehong.han.config.cache.RedisHelper;
import com.yehong.han.config.exception.GatewayException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HanYehong
 * @date 2019/4/1 22:26
 */
@Service
public class BusServiceImpl implements BusService {

    @Override
    public void uploadPointer(PointerRequest pointer) throws GatewayException {

        if (pointer.getBusCode() == null) {
            throw new GatewayException("公交代码有误，请确认");
        }

        if (pointer.getLatitude() == null || pointer.getLongitude() == null) {
            throw new GatewayException("上传的定位有误，请确认");
        }

        RedisHelper.getRedisUtil().setObject(CacheKey.BUS_PRE + pointer.getBusCode(), pointer);
    }

    @Override
    public List<PointerRequest> getAllPointer() {

        List<PointerRequest> pointerRequestList = new ArrayList<>();

        RedisHelper cache = RedisHelper.getRedisUtil();

        for (BusEnum busEnum : BusEnum.values()) {
            PointerRequest pointer =
                    (PointerRequest) cache.getObject(CacheKey.BUS_PRE + busEnum.getCode());
            if (pointer != null) {
                pointerRequestList.add(pointer);
            }
        }

        return pointerRequestList;
    }

    @Override
    public PointerRequest getSpecialPointer(Integer busCode) throws GatewayException {

        if (busCode == null) {
            throw new GatewayException("查询的公交不能为空");
        }

        PointerRequest pointerRequest =
                (PointerRequest) RedisHelper.getRedisUtil().getObject(CacheKey.BUS_PRE + busCode);

        if (pointerRequest == null) {
            throw new GatewayException("无指定公交的定点位置");
        }

        return pointerRequest;
    }
}
