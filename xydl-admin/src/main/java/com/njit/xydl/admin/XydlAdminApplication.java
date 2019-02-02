package com.njit.xydl.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.njit.xydl.admin.dao")
@SpringBootApplication
public class XydlAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlAdminApplication.class, args);
    }

}

