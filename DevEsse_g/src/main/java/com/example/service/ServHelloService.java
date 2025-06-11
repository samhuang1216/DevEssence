package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class ServHelloService {
    public String hello() {
        return "Hello from Service!";
    }
}
