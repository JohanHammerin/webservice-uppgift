package se.johan.webservice_uppgift.dto;

import jakarta.validation.constraints.NotBlank;

public class SendMessageRequest {

    @NotBlank(message = "Name can not be Blank")
    private String username;
    @NotBlank(message = "Password can not be Blank")
    private String password;
    private String body;
    @NotBlank(message = "Receiver can not be Blank")
    private String receiver;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public @NotBlank(message = "Receiver can not be Blank") String getReceiver() {
        return receiver;
    }

    public void setReceiver(@NotBlank(message = "Receiver can not be Blank") String receiver) {
        this.receiver = receiver;
    }
}
