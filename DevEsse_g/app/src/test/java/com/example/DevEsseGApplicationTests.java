package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.service.ServHelloService;

@SpringBootTest
class DevEsseGApplicationTests {

    @Autowired
    private ServHelloService servHelloService;

    @Test
    void contextLoads() {
        servHelloService.hello();
    }
}
