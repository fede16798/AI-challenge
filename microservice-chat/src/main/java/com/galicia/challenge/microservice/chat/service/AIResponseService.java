package com.galicia.challenge.microservice.chat.service;

import com.galicia.challenge.microservice.chat.client.IWeatherClient;
import com.galicia.challenge.microservice.chat.config.AIResponseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AIResponseService implements IAIResponseService{
    @Autowired
    private AIResponseConfig aiResponseConfig;
    @Autowired
    private IWeatherClient weatherClient;

    @Override
    public String getResponse(String question, String conversationId) {
        String questionLowerCase = question.toLowerCase();

        return aiResponseConfig.getAnswers().entrySet().stream()
                .filter(entry -> questionLowerCase.contains(entry.getKey()))
                .findFirst()
                .map(entry -> formatResponse(entry.getKey(), entry.getValue()))
                .orElse(aiResponseConfig.getAnswers().get("default"));
    }

    private String formatResponse(String key, String responseTemplate) {
        if (key.equals("weather")) {
            return String.format(responseTemplate, weatherClient.getWeather());
        } else if (key.equals("time")) {
            return String.format(responseTemplate, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        }
        return responseTemplate;
    }
}
