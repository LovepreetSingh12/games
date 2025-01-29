package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/health")
public class healthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(healthCheckController.class);

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("The service is running fine.");
    }

    @GetMapping("mongo")
    public ResponseEntity<String> getMongoHealth() {
        try {
            mongoTemplate.executeCommand("{ping : 1}");
            return ResponseEntity.ok("Mongo is UP");
        } catch (Exception e) {
            logger.error("Exception at healthCheckController.getMongoHealth: " + e.getMessage());
            return ResponseEntity.status(503).body("MongoDB is DOWN: " + e.getMessage());
        }
    }
    
    
}
