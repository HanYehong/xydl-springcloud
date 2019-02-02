package com.njit.xydl.consumer2.controller;

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
        //  此时的的xydl-provider是在注册中心获取的实例，不是yml文件里配置的实例列表
        String greeting = this.restTemplate.getForObject("http://xydl-provider2/greeting",String.class);
        return String.format("%s , %s!" ,greeting , name) ;
    }

}
