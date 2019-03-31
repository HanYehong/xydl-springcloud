package com.njit.xydl.life.express.service.impl;

import com.njit.xydl.life.express.dao.ExpressMapper;
import com.njit.xydl.life.express.entity.Express;
import com.njit.xydl.life.express.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author HanYehong
 */
@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public List<Express> listExpressOrderByStatus(int status) {
        return expressMapper.selectExpressOrderByStatus(status);
    }
}
