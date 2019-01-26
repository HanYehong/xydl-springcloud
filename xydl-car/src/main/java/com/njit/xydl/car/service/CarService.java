package com.njit.xydl.car.service;

import com.njit.xydl.car.entity.Car;
import com.njit.xydl.car.entity.CarExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarService {

    void entryCarLocation(int number, double longitude, double laitude);

    Map<String,Double> getCarLocation(int number);

    List<Map<String,Double>> getAllCarLocation();

    long countByExample(CarExample example);

    int deleteByExample(CarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    List<Car> selectByExample(CarExample example);

    Car selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Car record, @Param("example") CarExample example);

    int updateByExample(@Param("record") Car record, @Param("example") CarExample example);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
}
