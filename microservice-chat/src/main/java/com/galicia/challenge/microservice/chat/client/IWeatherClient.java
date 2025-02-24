package com.galicia.challenge.microservice.chat.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msvc-chat", url = "http://localhost:8080/api/weather", fallback = WeatherClientFallback.class)
public interface IWeatherClient {

    @GetMapping("")
    String getWeather();

}
