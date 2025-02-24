package com.galicia.challenge.microservice.weather.repository;

import org.springframework.stereotype.Repository;
import java.util.Random;

@Repository
public class WeatherRepository implements IWeatherRepository{
    private static final Random r = new Random();
    private static final String[] WEATHERS = new String[]{"Sunny", "Cloudy", "Windy", "Rainy", "Foggy"};

    @Override
    public String getWeather(String conversationId) {
        return WEATHERS[ r.nextInt(WEATHERS.length)];
    }
}
