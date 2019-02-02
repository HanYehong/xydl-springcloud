package com.njit.xydl.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Bean
    @LoadBalanced  //负载均衡，ribbon时要加
    RestTemplate restTemplate(){
        return new RestTemplate() ;
    }

    @Autowired
    private RestTemplate restTemplate ;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value="name" , defaultValue = "springcloud") String name){
        String greeting = this.restTemplate.getForObject("http://xydl-provider/greeting",String.class);
        return String.format("%s , %s!" ,greeting , name) ;
    }

}
