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
        String lon = "", la = "";
        if( (lon = (String)redis.hmGet("car"+number,"longitude")) == null){
            return null;
        }
        if( (la = (String)redis.hmGet("car"+number,"laitude")) == null){
            return null;
        }
        map.put("longitude", Double.parseDouble(lon));
        map.put("laitude", Double.parseDouble(la));
        return map;
    }

    /**
     * 得到所有小公交的定位
     * @return
     */
    @Override
    public List<Map<String, Double>> getAllCarLocation() {
        List<Map<String, Double>> all = new ArrayList<>();
        Set<Object> name = getAllCarName();
        for(Iterator<Object> iterator = name.iterator(); iterator.hasNext(); ){
            Map<String, Double> map = getCarLocation(Integer.parseInt((String)iterator.next()));
            if(map != null) all.add(map);
        }
        return all;
    }

    /**
     * 从缓存中读取所有小车编号
     * 若缓存中不存在则从数据库中查询 再将数据放到缓存中
     * @return
     */
    @Override
    public Set<Object> getAllCarName() {
        Set<Object> set;
        if( (set = redis.setMembers("cars")).size() != 0){
            System.out.println("从缓存中取数据");
            System.out.println(set.toString());
            System.out.println(set.size());
            return set;
        }
        System.out.println("从数据库查询");
        List<Car> list = selectByExample(null);
        set = new HashSet<>();
        for(Iterator<Car> iterator = list.iterator(); iterator.hasNext(); ){
            String s = String.valueOf(iterator.next().getCarNumber());
            set.add(s);
            redis.add("cars",s);
        }
        return set;
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
