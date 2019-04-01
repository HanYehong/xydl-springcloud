package com.njit.xydl.atlas.bus.service;

import com.njit.xydl.atlas.bus.controller.request.PointerRequest;
import com.yehong.han.config.exception.GatewayException;
import org.hibernate.validator.constraints.SafeHtml;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/4/1 22:26
 */
public interface BusService {

    /**
     * 安卓端service上传坐标点到Redis数据库中
     * @param pointer
     */
    void uploadPointer(PointerRequest pointer) throws GatewayException;

    /**
     * 获得所有公交坐标点
     * @return
     */
    List<PointerRequest> getAllPointer();

    /**
     * 获得指定公交坐标点
     * @param busCode
     * @return
     */
    PointerRequest getSpecialPointer(Integer busCode) throws GatewayException;
}
