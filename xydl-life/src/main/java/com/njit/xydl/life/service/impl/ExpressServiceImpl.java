package com.njit.xydl.life.service.impl;

import com.njit.xydl.life.dao.ExpressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpressServiceImpl {

    @Autowired
    private ExpressMapper expressMapper;

}
