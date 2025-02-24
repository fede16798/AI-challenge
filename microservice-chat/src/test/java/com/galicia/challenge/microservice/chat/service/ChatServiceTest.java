package com.galicia.challenge.microservice.chat.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @InjectMocks
    private ChatService chatService;

    @Mock
    private AIResponseService aiResponseService;

    private static final String CONVERSATION_ID = "12345";

    @Test
    void testReceiveQuestion_Weather() {
        String inputMessage = "¿Cuál es el clima hoy?";
        String expectedResponse = "El clima está soleado";

        Mockito.when(aiResponseService.getResponse(inputMessage, CONVERSATION_ID)).thenReturn(expectedResponse);

        String actualResponse = chatService.receiveQuestion(inputMessage, CONVERSATION_ID);

        assertEquals(expectedResponse, actualResponse);
        Mockito.verify(aiResponseService, times(1)).getResponse(inputMessage, CONVERSATION_ID);
    }

    @Test
    void testReceiveQuestion_Time() {
        String inputMessage = "¿Qué hora es?";
        String expectedResponse = "La hora actual es: 24-02-2025 09:30:23";

        Mockito.when(aiResponseService.getResponse(inputMessage, CONVERSATION_ID)).thenReturn(expectedResponse);

        String actualResponse = chatService.receiveQuestion(inputMessage, CONVERSATION_ID);

        assertEquals(expectedResponse, actualResponse);
        Mockito.verify(aiResponseService, times(1)).getResponse(inputMessage, CONVERSATION_ID);
    }

    @Test
    void testReceiveQuestion_Unknown() {
        String inputMessage = "¿Cómo está el tráfico?";
        String expectedResponse = "No pude entender tu pregunta, lo siento!";

        Mockito.when(aiResponseService.getResponse(inputMessage, CONVERSATION_ID)).thenReturn(expectedResponse);
        String actualResponse = chatService.receiveQuestion(inputMessage, CONVERSATION_ID);

        assertEquals(expectedResponse, actualResponse);
        Mockito.verify(aiResponseService, times(1)).getResponse(inputMessage, CONVERSATION_ID);
    }
}

