package com.njit.xydl.life.service;

import com.njit.xydl.life.entity.Express;

import java.util.List;

public interface ExpressService {

    /**
     * 列出所有待接的快递单
     * @return
     */
    List<Express> listAllExpressOrder();

}
