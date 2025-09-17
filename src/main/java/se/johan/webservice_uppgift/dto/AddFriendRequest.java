package se.johan.webservice_uppgift.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record AddFriendRequest(
        @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "Username can not be BLANK")
        String username,
        @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "Password can not be BLANK")
        String password,
        @Max(value = 50, message = "Maximum characters is 50")@NotBlank(message = "FriendUsername can not be BLANK")
        String friendUsername
) {}