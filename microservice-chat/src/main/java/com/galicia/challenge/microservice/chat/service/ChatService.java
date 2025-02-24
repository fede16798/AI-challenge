package com.galicia.challenge.microservice.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements IChatService{

    @Autowired
    private AIResponseService aiResponseService;

    @Override
    public String receiveQuestion(String message, String conversationId) {
        return aiResponseService.getResponse(message, conversationId);
    }

}
