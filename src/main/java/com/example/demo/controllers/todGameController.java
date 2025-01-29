package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.todGameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/todgame")
public class todGameController {
    
    @Autowired
    private todGameService todGameService;

    @GetMapping("getquestion")
    public ResponseEntity<String> getTruth(@RequestParam String rating, @RequestParam String type) {
        String res = todGameService.getQuestion(type, rating);
        if(res == null || res.trim().isEmpty()) {
            return ResponseEntity.status(503).body("API unreachable");
        }
        return ResponseEntity.ok(res);
    }
}
