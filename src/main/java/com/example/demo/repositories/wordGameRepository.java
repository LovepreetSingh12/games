package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.models.wordGameModel;

public interface wordGameRepository extends MongoRepository<wordGameModel, String> {
    wordGameModel findByGameCode(String gameCode);
    void deleteByGameCode(String gameCode);
}
