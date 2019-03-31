package com.njit.xydl.atlas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.njit.xydl.atlas.*.dao")
@SpringBootApplication
public class XydlAtlasApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlAtlasApplication.class, args);
    }

}
