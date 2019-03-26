package com.njit.xydl.users;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@MapperScan("com.njit.xydl.users.dao")
@SpringBootApplication
public class XydlUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlUsersApplication.class, args);
    }

}

