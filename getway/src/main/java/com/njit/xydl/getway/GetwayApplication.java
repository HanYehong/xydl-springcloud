package com.njit.xydl.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class GetwayApplication {

    /**
     * 引入Zuul过滤器到容器中
     */
    @Bean
    public SimpleFilter simpleFilter(){
        return new SimpleFilter() ;
    }

    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class, args);
    }

}

