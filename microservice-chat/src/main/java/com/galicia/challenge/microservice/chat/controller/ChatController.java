package com.galicia.challenge.microservice.chat.controller;

import com.galicia.challenge.microservice.chat.model.ContextPayload;
import com.galicia.challenge.microservice.chat.model.MessageRequestPayload;
import com.galicia.challenge.microservice.chat.model.MessageResponsePayload;
import com.galicia.challenge.microservice.chat.service.IChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@Validated
public class ChatController {

    @Autowired
    private IChatService chatService;

    @PostMapping
    public ResponseEntity<MessageResponsePayload> receiveQuestion (@RequestHeader(value = "X-Conversation-Id", required = false) String conversationId, @Valid @RequestBody MessageRequestPayload requestPayload) {
        if (conversationId == null)
            conversationId = UUID.randomUUID().toString();

        String messageResponse = chatService.receiveQuestion(requestPayload.getMessage(), conversationId);

        MessageResponsePayload messageResponsePayload = MessageResponsePayload.builder()
                .messageResponse(messageResponse)
                .context(ContextPayload.builder()
                        .timestamp(LocalDateTime.now())
                        .conversationId(conversationId)
                        .build())

                .build();

        return new ResponseEntity<>(messageResponsePayload, HttpStatus.OK);
    }

}
