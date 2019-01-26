package com.njit.xydl.map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.njit.xydl.map.dao")
@SpringBootApplication
public class XydlAtlasApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlAtlasApplication.class, args);
    }

}

