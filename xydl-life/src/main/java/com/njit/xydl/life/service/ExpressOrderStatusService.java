package com.njit.xydl.life.service;

import com.njit.xydl.life.entity.ExpressOrderStatus;

import java.util.List;

public interface ExpressOrderStatusService {
    List<ExpressOrderStatus> selectMyAcceptOrder(String weixinID);
}
