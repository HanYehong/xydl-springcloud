package com.njit.xydl.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class XydlRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlRobotApplication.class, args);
    }

}

