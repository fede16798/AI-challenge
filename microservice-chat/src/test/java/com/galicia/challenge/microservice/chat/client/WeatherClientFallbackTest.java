package com.galicia.challenge.microservice.chat.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherClientFallbackTest {


    private final WeatherClientFallback weatherClientFallback = new WeatherClientFallback();

    @Test
    void getWeather_ShouldReturnFallbackMessage() {
        String expectedResponse = "It's not possible to get the weather information right now";
        String actualResponse = weatherClientFallback.getWeather();

        assertEquals(expectedResponse, actualResponse);
    }
}
