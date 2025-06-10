package com.example.controller;

import com.example.service.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class HelloController {

    private final SampleService sampleService;

    public HelloController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring MVC!";
    }

    @GetMapping("/names")
    public CompletableFuture<List<String>> names() {
        return sampleService.getNames();
    }
}
