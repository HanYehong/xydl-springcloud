package com.njit.xydl.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class XydlEurekaService2Application {

    public static void main(String[] args) {
        SpringApplication.run(XydlEurekaService2Application.class, args);
    }

}

