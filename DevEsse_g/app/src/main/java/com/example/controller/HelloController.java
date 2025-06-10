package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.ServHelloService;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final ServHelloService servHelloService;

    @GetMapping("/hello")
    public String hello() {
        return servHelloService.hello();
    }
}
