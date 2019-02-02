package com.njit.xydl.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class XydlUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlUserApplication.class, args);
    }

}

