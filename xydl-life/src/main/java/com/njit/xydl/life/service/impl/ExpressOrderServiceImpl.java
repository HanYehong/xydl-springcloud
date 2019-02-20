package com.njit.xydl.life.service.impl;

import com.njit.xydl.life.dao.ExpressOrderMapper;
import com.njit.xydl.life.dao.ExpressOrderStatusMapper;
import com.njit.xydl.life.entity.ExpressOrder;
import com.njit.xydl.life.entity.ExpressOrderExample;
import com.njit.xydl.life.entity.ExpressOrderStatusExample;
import com.njit.xydl.life.service.ExpressOrderService;
import com.sun.jndi.cosnaming.ExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpressOrderServiceImpl implements ExpressOrderService {
    
    @Autowired
    private ExpressOrderMapper expressOrderMapper;

    /**
     * 在首页展示所有订单
     * @return
     */
    @Override
    public List<ExpressOrder> selectAllOrder() {
        return expressOrderMapper.selectByExample(null);
    }

    /**
     * 展示所有我发布的订单
     * @param weixinID
     * @return
     */
    @Override
    public List<ExpressOrder> selectMyPublishOrder(String weixinID) {
        ExpressOrderExample example = new ExpressOrderExample();
        example.createCriteria().andNameEqualTo(weixinID);
        example.createCriteria().andDelEqualTo(false);
        return expressOrderMapper.selectByExample(example);
    }

    @Override
    public long countByExample(ExpressOrderExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ExpressOrderExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(ExpressOrder record) {
        return 0;
    }

    @Override
    public int insertSelective(ExpressOrder record) {
        return 0;
    }

    @Override
    public List<ExpressOrder> selectByExample(ExpressOrderExample example) {
        return null;
    }

    @Override
    public ExpressOrder selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(ExpressOrder record, ExpressOrderExample example) {
        return 0;
    }

    @Override
    public int updateByExample(ExpressOrder record, ExpressOrderExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ExpressOrder record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ExpressOrder record) {
        return 0;
    }
}
