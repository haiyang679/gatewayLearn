package com.jhy.testRoute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/hello")
    public String testHello(){
        return "后端服务返回数据";
    }
}
