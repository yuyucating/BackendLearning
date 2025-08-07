package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello") // 到這個路徑下使用 'Get'
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/hello2")
    public int hello2(){
        return 123;
    }

    @PostMapping("/post")
    public String postTest(){
        return "post";
    }

    @PutMapping("/put")
    public String putTest(){
        return "This is PUT!";
    }
}
