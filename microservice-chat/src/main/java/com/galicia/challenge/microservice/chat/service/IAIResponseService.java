package com.galicia.challenge.microservice.chat.service;

public interface IAIResponseService {

    String getResponse(String question, String conversationId);
}
