package com.njit.xydl.reading.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class BookService {

    @HystrixCommand(fallbackMethod = "reliable")
    public String readingList() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create("http://localhost:3333/recommended");
        return restTemplate.getForObject(uri, String.class);
    }

    /**
     * 回调方法
     */
    public String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }
}
