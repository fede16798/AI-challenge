package com.galicia.challenge.microservice.chat.handler;

import com.galicia.challenge.microservice.chat.model.ContextPayload;
import com.galicia.challenge.microservice.chat.model.ErrorResponsePayload;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getConversationId(HttpServletRequest request) {
        return request.getHeader("X-Conversation-Id");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponsePayload handleApiBadRequestException(MethodArgumentNotValidException ex,  HttpServletRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ErrorResponsePayload.builder()
                .errorMessagesList(errors)
                .context(ContextPayload.builder()
                            .timestamp(LocalDateTime.now())
                            .conversationId(getConversationId(request))
                        .build())
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponsePayload handleInvalidJson(HttpMessageNotReadableException ex,  HttpServletRequest request) {
        return ErrorResponsePayload.builder()
                .errorMessage("Invalid JSON format")
                .context(ContextPayload.builder()
                            .timestamp(LocalDateTime.now())
                            .conversationId(getConversationId(request))
                        .build())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponsePayload handleGenericException(Exception ex, HttpServletRequest request) {
         return ErrorResponsePayload.builder()
                .errorMessage(ex.getMessage())
                 .context(ContextPayload.builder()
                             .timestamp(LocalDateTime.now())
                             .conversationId(getConversationId(request))
                         .build())
                .build();
    }
}
