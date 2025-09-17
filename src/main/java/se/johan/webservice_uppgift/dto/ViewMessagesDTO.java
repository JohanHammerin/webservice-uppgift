package se.johan.webservice_uppgift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ViewMessagesDTO(
        @Size(max = 50, message = "Maximum characters is 50") @NotBlank String username,
        @Size(max = 50, message = "Maximum characters is 50")@NotBlank String password)
{}


