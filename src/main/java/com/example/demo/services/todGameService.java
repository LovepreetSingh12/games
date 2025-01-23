package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.models.todModel;

@Service
public class todGameService {

    private Environment environment;

    private final WebClient webClient;

    private static final Logger logger = LoggerFactory.getLogger(todGameService.class);
    
    @Autowired
    public todGameService(WebClient.Builder webClientBuilder, Environment environment) {
        this.environment = environment;
        String baseUrl = this.environment.getProperty("app.todapi.url");
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }
    public String getQuestion(String type, String rating) {
        String res = "";
        try {
            todModel truth = webClient.get().uri(uriBuilder -> uriBuilder
                                                    .path("/" + type)
                                                    .queryParam("rating", rating)
                                                    .build()
                                                )
                                            .retrieve()
                                            .bodyToMono(todModel.class)
                                            .block();
            if(truth != null) {
                res = truth.getQuestion();
            }
        } catch (Exception e) {
            logger.error("Exception at todGameService.getTruth: " + e.getMessage());
        }
        return res;
    }
}
