package se.johan.webservice_uppgift.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
    @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "Username can not be Blank")
    String username,
    @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "Password can not be Blank")
    String password)
{}

