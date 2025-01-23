package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/health")
public class healthCheckController {
    
    @GetMapping
    public String healthCheck() {
        return "The service is running fine.";
    }

    @GetMapping("mongo")
    public String getMongoHealth(@RequestParam String param) {
        return new String();
    }
    
    
}
