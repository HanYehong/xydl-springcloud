package com.njit.xydl.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.njit.xydl.car.dao")
@SpringBootApplication
public class XydlCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlCarApplication.class, args);
    }

}

