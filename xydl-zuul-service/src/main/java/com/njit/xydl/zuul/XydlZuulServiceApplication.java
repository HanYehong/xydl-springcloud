package com.njit.xydl.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class XydlZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlZuulServiceApplication.class, args);
    }

}

