package com.galicia.challenge.microservice.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galicia.challenge.microservice.chat.model.MessageRequestPayload;
import com.galicia.challenge.microservice.chat.service.IChatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ChatController.class)
class ChatControllerTest {

    private static final String CHAT_URL = "/api/chat";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IChatService chatService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void receiveQuestion_ShouldReturnValidResponse() throws Exception {
        String message = "clima";
        String expectedResponse = "El clima está soleado";

        when(chatService.receiveQuestion(any(String.class), any(String.class)))
                .thenReturn(expectedResponse);

        MessageRequestPayload requestPayload = MessageRequestPayload.builder()
                .message(message)
                .build();

        mockMvc.perform(post(CHAT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestPayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageResponse", is(expectedResponse)))
                .andExpect(jsonPath("$.context.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.context.conversationId", is(notNullValue())));
    }

    @Test
    void receiveQuestion_ShouldReturnBadRequest_WhenMessageIsMissing() throws Exception {
        MessageRequestPayload requestPayload = new MessageRequestPayload();

        mockMvc.perform(post(CHAT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestPayload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessagesList", hasItem("Message must be a sentence.")));
    }

    @Test
    void receiveQuestion_ShouldHandleServiceFailure() throws Exception {
        when(chatService.receiveQuestion(any(String.class), any(String.class)))
                .thenThrow(new RuntimeException("Service failure"));

        MessageRequestPayload requestPayload = MessageRequestPayload.builder()
                .message("clima")
                .build();

        mockMvc.perform(post(CHAT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestPayload)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage", is("Service failure")));
    }
}