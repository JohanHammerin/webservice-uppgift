package se.johan.webservice_uppgift.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record SendMessageRequest (

    @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "Name can not be Blank")
    String username,
    @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "Password can not be Blank")
    String password,
    @Max(value = 500, message = "Maximum characters is 500")@NotBlank(message = "Message can not be Blank") String body,
    @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "Receiver can not be Blank")
    String receiver
    )

    {}