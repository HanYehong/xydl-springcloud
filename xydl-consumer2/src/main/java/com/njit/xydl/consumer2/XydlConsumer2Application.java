package com.njit.xydl.consumer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
public class XydlConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(XydlConsumer2Application.class, args);
    }

}
