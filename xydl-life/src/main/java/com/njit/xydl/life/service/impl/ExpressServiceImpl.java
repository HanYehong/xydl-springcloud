package com.njit.xydl.life.service.impl;

import com.njit.xydl.life.dao.ExpressMapper;
import com.njit.xydl.life.entity.Express;
import com.njit.xydl.life.service.ExpressService;
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
    public List<Express> listAllExpressOrder() {
        return expressMapper.selectAllUnAccept();
    }
}
