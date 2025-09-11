package se.johan.webservice_uppgift.dto;

import jakarta.validation.constraints.NotBlank;

public class AddFriendRequest {
    @NotBlank(message = "Username can not be BLANK")
    String username;
    @NotBlank (message = "Password can not be BLANK")
    String password;
    @NotBlank (message = "FriendUsername can not be BLANK")
    String friendUsername;

    public @NotBlank(message = "Username can not be BLANK") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username can not be BLANK") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password can not be BLANK") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password can not be BLANK") String password) {
        this.password = password;
    }

    public @NotBlank(message = "FriendUsername can not be BLANK") String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(@NotBlank(message = "FriendUsername can not be BLANK") String friendUsername) {
        this.friendUsername = friendUsername;
    }
}
