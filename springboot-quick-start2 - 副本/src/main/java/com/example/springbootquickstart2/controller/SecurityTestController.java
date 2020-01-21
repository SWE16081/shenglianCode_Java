package com.example.springbootquickstart2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {
    @GetMapping("/securityTest")
    public  String securityTest(){
        return "安全测试";
    }
}
