package com.galicia.challenge.microservice.chat.service;

public interface IChatService {

    String receiveQuestion(String message, String conversationId);
}
