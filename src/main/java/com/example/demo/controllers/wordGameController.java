package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.wordGameModel;
import com.example.demo.services.wordGameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/wordgame")
public class wordGameController {

    @Autowired
    private wordGameService wordGameService;

    @PostMapping("start")
    public String startGame(@RequestBody wordGameModel game) {
        return wordGameService.startGame(game);
    }
    
    @PostMapping("check")
    public String checkGame(@RequestParam String gameCode, @RequestParam String guess) {
        return wordGameService.checkGame(gameCode, guess);
    }

    @DeleteMapping("finish")
    public String finishGame(@RequestParam String gameCode) {
        return wordGameService.finishGame(gameCode);
    }
}