package se.johan.webservice_uppgift.dto;


import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank(message = "Name can not be Blank")
    public String username;
    @NotBlank(message = "Password can not be Blank")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
