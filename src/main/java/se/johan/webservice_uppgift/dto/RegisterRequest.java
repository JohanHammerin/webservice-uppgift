package se.johan.webservice_uppgift.dto;


import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank(message = "Name can not be Blank")
    public String username;
    @NotBlank(message = "Password can not be Blank")
    public String password;

    public @NotBlank(message = "Name can not be Blank") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Name can not be Blank") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password can not be Blank") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password can not be Blank") String password) {
        this.password = password;
    }
}
