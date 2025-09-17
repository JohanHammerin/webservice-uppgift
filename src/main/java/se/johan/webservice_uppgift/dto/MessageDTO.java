package se.johan.webservice_uppgift.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record MessageDTO(
       @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "Sender can not be Blank") String sender,
        @Max(value = 500, message = "Maximum characters is 500")@NotBlank(message = "Body can not be Blank") String body,
        LocalDateTime timestamp
) {}
