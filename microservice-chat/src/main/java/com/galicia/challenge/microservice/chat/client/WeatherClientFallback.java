package com.galicia.challenge.microservice.chat.client;

import org.springframework.stereotype.Component;

@Component
public class WeatherClientFallback implements IWeatherClient {
    @Override
    public String getWeather() {
        return "It's not possible to get the weather information right now";
    }
}