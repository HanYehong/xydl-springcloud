package com.njit.xydl.car.service.impl;

import com.njit.xydl.car.dao.CarMapper;
import com.njit.xydl.car.entity.Car;
import com.njit.xydl.car.entity.CarExample;
import com.njit.xydl.car.service.CarService;
import com.njit.xydl.car.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private RedisUtil redis;

    @Autowired
    private CarMapper carMapper;

    /**
     * 在redis缓存中存入编号为number小公交的定位
     * @param longitude
     * @param laitude
     */
    @Override
    public void entryCarLocation(int number, double longitude, double laitude) {
        if(longitude == 0 || laitude == 0){
            redis.remove("car"+number);
        }
        redis.hmSet("car"+number,"longitude", String.valueOf(longitude));
        redis.hmSet("car"+number,"laitude", String.valueOf(laitude));
    }

    /**
     * 得到指定编号的小公交的定位
     * @param number
     * @return
     */
    @Override
    public Map<String, Double> getCarLocation(int number) {
        Map<String, Double> map = new HashMap<>();
        map.put("longitude", Double.parseDouble((String)redis.hmGet("car"+number,"longitude")));
        map.put("laitude", Double.parseDouble((String)redis.hmGet("car"+number,"laitude")));
        return map.get("longitude") == null || map.get("laitude") == null ? null : map;
    }

    /**
     * 得到所有小公交的定位
     * @return
     */
    @Override
    public List<Map<String, Double>> getAllCarLocation() {
        List<Map<String, Double>> all = new ArrayList<>();
        List<Car> list = selectByExample(null);
        for(Iterator<Car> iterator = list.iterator(); iterator.hasNext(); ){
            Map<String, Double> map = getCarLocation(iterator.next().getCarNumber());
            if(map != null) all.add(map);
        }
        return all;
    }

    @Override
    public long countByExample(CarExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CarExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Car record) {
        return 0;
    }

    @Override
    public int insertSelective(Car record) {
        return 0;
    }

    @Override
    public List<Car> selectByExample(CarExample example) {
        return carMapper.selectByExample(example);
    }

    @Override
    public Car selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Car record, CarExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Car record, CarExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Car record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Car record) {
        return 0;
    }

}
