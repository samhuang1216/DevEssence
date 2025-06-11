package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.ServHelloService;
import com.example.service.SampleService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final ServHelloService servHelloService;
    private final SampleService sampleService;

    @GetMapping("/hello")
    public String hello() {
        return servHelloService.hello();
    }

    @GetMapping("/names")
    public CompletableFuture<List<String>> names() {
        return sampleService.getNames();
    }
}