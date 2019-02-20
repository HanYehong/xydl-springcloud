package com.njit.xydl.life.service.impl;

import com.njit.xydl.life.dao.ExpressOrderStatusMapper;
import com.njit.xydl.life.entity.ExpressOrderStatus;
import com.njit.xydl.life.entity.ExpressOrderStatusExample;
import com.njit.xydl.life.service.ExpressOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpressOrderStatusServiceImpl implements ExpressOrderStatusService {
    @Autowired
    private ExpressOrderStatusMapper statusMapper;

    /**
     * 展示所有我接的订单
     * @param weixinID
     * @return
     */
    @Override
    public List<ExpressOrderStatus> selectMyAcceptOrder(String weixinID) {
        ExpressOrderStatusExample statusExample = new ExpressOrderStatusExample();
        statusExample.createCriteria().andManEqualTo(weixinID);
        return statusMapper.selectByExample(statusExample);
    }
}
