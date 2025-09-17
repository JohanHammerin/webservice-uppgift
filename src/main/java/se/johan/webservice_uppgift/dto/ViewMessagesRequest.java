package se.johan.webservice_uppgift.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record ViewMessagesRequest (
        @Max(value = 50, message = "Maximum characters is 50") @NotBlank String username,
        @Max(value = 50, message = "Maximum characters is 50")@NotBlank String password)
{}


