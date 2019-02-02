package com.njit.xydl.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@RibbonClient(name = "xydl-provider", configuration = XydlProviderConfiguration.class)
@SpringBootApplication
public class XydlConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlConsumerApplication.class, args);
    }

}
