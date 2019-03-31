package com.njit.xydl.life;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.njit.xydl.life.*.dao")
@SpringBootApplication
public class XydlLifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlLifeApplication.class, args);
    }

}
