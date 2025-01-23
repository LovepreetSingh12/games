package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.wordGameModel;
import com.example.demo.repositories.wordGameRepository;

@Service
public class wordGameService {
    
    @Autowired
    private wordGameRepository wordGameRepository;

    public String startGame(wordGameModel game) {
        String result = "";
        try {
            wordGameRepository.save(game);
            result = "game started with game code : " + game.getGameCode();
        } catch (Exception e) {
            result = "error starting the game with server error: " + e.getMessage();
        }
        return result;
    }

    public String checkGame(String gameCode, String guess) {
        String result = "";
        try {
            wordGameModel game = wordGameRepository.findByGameCode(gameCode);
            String word = game.getWord();
            int length = word.length();
            StringBuilder sb = new StringBuilder();
            
            for(int i=0;i<length;++i) {
                sb.append(word.charAt(i) == guess.charAt(i) ? '0' : 'X');
            }
            result = sb.toString();
        } catch (Exception e) {
            result = "error checking the guess with server error: " + e.getMessage();
        }
        return result;
    }

    public String finishGame(String gameCode) {
        String result = "";
        try {
            wordGameRepository.deleteByGameCode(gameCode);
            result = "game finished";
        } catch (Exception e) {
            result = "error finishing the game with server error: " + e.getMessage();
        }
        return result;
    }
}
