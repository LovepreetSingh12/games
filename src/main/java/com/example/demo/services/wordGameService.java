package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.wordGameModel;
import com.example.demo.repositories.wordGameRepository;

@Service
public class wordGameService {

    private static final Logger logger = LoggerFactory.getLogger(wordGameService.class);
    
    @Autowired
    private wordGameRepository wordGameRepository;

    public String startGame(wordGameModel game) {
        String result = "";
        try {
            wordGameRepository.save(game);
            result = "game started with game code : " + game.getGameCode();
            logger.info("word game saved with game code : " + game.getGameCode());
        } catch (Exception e) {
            result = "error starting the game with server error: " + e.getMessage();
            logger.error("Exception at wordGameService.startGame: " + e.getMessage());
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
            logger.error("Exception at wordGameService.checkGame: " + e.getMessage());
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
            logger.error("Exception at wordGameService.finishGame: " + e.getMessage());
        }
        return result;
    }
}
