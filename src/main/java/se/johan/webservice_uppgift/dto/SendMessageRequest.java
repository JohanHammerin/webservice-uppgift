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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver( String receiver) {
        this.receiver = receiver;
    }
}
