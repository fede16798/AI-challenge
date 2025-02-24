package com.galicia.challenge.microservice.weather.service;

import com.galicia.challenge.microservice.weather.repository.IWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService implements IWeatherService{

    @Autowired
    private IWeatherRepository weatherRepository;

    public String getWeather(String conversationId) {
        return weatherRepository.getWeather(conversationId);
    }
}
