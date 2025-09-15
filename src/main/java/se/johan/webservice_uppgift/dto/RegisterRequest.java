package se.johan.webservice_uppgift.dto;


import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank(message = "Username can not be Blank")
    private String username;
    @NotBlank(message = "Password can not be Blank")
    private String password;

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
