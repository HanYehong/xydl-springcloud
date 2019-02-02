package com.njit.xydl.reading.controller;

import com.njit.xydl.reading.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class TestController {
    @Autowired
    private BookService bookService;

    // 此种方式不能起到融合熔断的作用，因此如果服务提供者故障，程序得到500异常。
    @RequestMapping("/to-read")
    public String readingList(){
        return bookService.readingList();
    }

}
