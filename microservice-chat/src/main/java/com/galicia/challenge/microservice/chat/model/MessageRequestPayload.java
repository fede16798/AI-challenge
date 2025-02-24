package com.galicia.challenge.microservice.chat.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestPayload {

    @NotBlank(message = "Message must be a sentence.")
    private String message;
}
