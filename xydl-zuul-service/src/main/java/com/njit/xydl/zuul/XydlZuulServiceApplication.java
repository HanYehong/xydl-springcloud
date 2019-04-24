package com.njit.xydl.zuul;

import com.njit.xydl.zuul.interceptor.AuthorizeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class XydlZuulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XydlZuulServiceApplication.class, args);
    }

   /* @Bean
    public AuthorizeFilter authorizeFilter() {
        return new AuthorizeFilter();
    }*/

}

