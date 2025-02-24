package com.galicia.challenge.microservice.weather.controller;

import com.galicia.challenge.microservice.weather.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private IWeatherService weatherService;


    @GetMapping
    public String getWeather (@RequestHeader(value = "X-Conversation-Id", required = false) String conversationId) {
        if (conversationId == null)
            conversationId = UUID.randomUUID().toString();

        return weatherService.getWeather(conversationId);
    }
}
