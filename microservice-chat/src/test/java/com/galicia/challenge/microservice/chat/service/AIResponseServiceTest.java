package com.galicia.challenge.microservice.chat.service;

import com.galicia.challenge.microservice.chat.client.IWeatherClient;
import com.galicia.challenge.microservice.chat.config.AIResponseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AIResponseServiceTest {

    @InjectMocks
    private AIResponseService aiResponseService;

    @Mock
    private AIResponseConfig aiResponseConfig;

    @Mock
    private IWeatherClient weatherClient;

    private static final String CONVERSATION_ID = "12345";

    @BeforeEach
    void setUp() {
        Map<String, String> answers = new HashMap<>();
        answers.put("weather", "The weather is %s.");
        answers.put("time", "The time is %s.");
        answers.put("default", "I didn't understand your question.");

        Mockito.when(aiResponseConfig.getAnswers()).thenReturn(answers);
    }

    @Test
    void getResponse_ShouldReturnWeather_WhenQuestionContainsWeather() {
        String question = "What's the weather today?";
        String expectedWeather = "Sunny";
        String expectedResponse = "The weather is Sunny.";

        Mockito.when(weatherClient.getWeather()).thenReturn(expectedWeather);

        String actualResponse = aiResponseService.getResponse(question, CONVERSATION_ID);

        assertEquals(expectedResponse, actualResponse);
        Mockito.verify(weatherClient, times(1)).getWeather();
    }

    @Test
    void getResponse_ShouldReturnTime_WhenQuestionContainsTime() {
        String question = "What time is it?";

        String actualResponse = aiResponseService.getResponse(question, CONVERSATION_ID);

        assertTrue(actualResponse.startsWith("The time is "));
    }

    @Test
    void getResponse_ShouldReturnDefault_WhenQuestionIsUnknown() {
        String question = "How's the traffic?";
        String expectedResponse = "I didn't understand your question.";

        String actualResponse = aiResponseService.getResponse(question, CONVERSATION_ID);

        assertEquals(expectedResponse, actualResponse);
    }
}