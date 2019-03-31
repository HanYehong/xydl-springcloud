package com.njit.xydl.life.express.service;

import com.njit.xydl.life.express.entity.Express;

import java.util.List;

/**
 * @author yehong.han
 */
public interface ExpressService {

    /**
     * 列出所有待接的快递单
     * @param status
     * @return
     */
    List<Express> listExpressOrderByStatus(int status);

}
