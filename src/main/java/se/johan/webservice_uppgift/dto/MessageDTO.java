package se.johan.webservice_uppgift.dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private String sender;
    private String body;
    private LocalDateTime timestamp;


    public MessageDTO(String sender, String body, LocalDateTime timestamp) {
        this.sender = sender;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timeStamp) {
        this.timestamp = timeStamp;
    }
}
