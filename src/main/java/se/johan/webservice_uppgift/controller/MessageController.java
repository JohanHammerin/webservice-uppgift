package se.johan.webservice_uppgift.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.johan.webservice_uppgift.dto.SendMessageRequest;
import se.johan.webservice_uppgift.model.Message;
import se.johan.webservice_uppgift.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendNew")
    public ResponseEntity<Message> sendNewMessage(@RequestBody SendMessageRequest request) {
        return messageService.sendMessage(
                        request.getUsername(),
                        request.getPassword(),
                        request.getBody(),
                        request.getReceiver()
                ).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).build());
    }

}

