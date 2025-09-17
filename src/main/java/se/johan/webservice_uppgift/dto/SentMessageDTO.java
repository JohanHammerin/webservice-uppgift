package se.johan.webservice_uppgift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record SentMessageDTO(
        String receiver,

        String body,
        LocalDateTime timestamp
) {
}
