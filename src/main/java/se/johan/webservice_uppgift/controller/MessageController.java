package se.johan.webservice_uppgift.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.johan.webservice_uppgift.dto.MessageDTO;
import se.johan.webservice_uppgift.dto.SendMessageRequest;
import se.johan.webservice_uppgift.dto.ViewMessagesRequest;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.model.Message;
import se.johan.webservice_uppgift.repository.ChatUserRepository;
import se.johan.webservice_uppgift.repository.MessageRepository;
import se.johan.webservice_uppgift.service.MessageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final MessageRepository messageRepository;

    public MessageController(MessageService messageService, MessageRepository messageRepository) {
        this.messageService = messageService;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/sendNew")
    public ResponseEntity<Message> sendNewMessage(@RequestBody SendMessageRequest request) {
        Optional<Message> message = messageService.sendMessage(
                request.getUsername(),
                request.getPassword(),
                request.getBody(),
                request.getReceiver()
        );

        if (message.isPresent()) {
            return ResponseEntity.ok(message.get());
        } else {
            return ResponseEntity.status(401).build();
        }
    }


    @DeleteMapping("/deleteLatest")
    public ResponseEntity<Message> deleteLatestMessage(@RequestBody SendMessageRequest request){
        return messageService.deleteMessage(
                request.getUsername(),
                request.getPassword(),
                request.getReceiver()
        ).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }


    @GetMapping("/viewMessages")
    public ResponseEntity<?> viewMessages(@RequestBody ViewMessagesRequest request) {
        List<MessageDTO> messages = messageService.viewMessages(
                request.getUsername(),
                request.getPassword()
        );

        if (messages == null) {
            return ResponseEntity.status(401).body("Fel användarnamn eller lösenord");
        } else if (messages.isEmpty()) {
            return ResponseEntity.ok("Inga meddelanden att visa");
        } else {
            return ResponseEntity.ok(messages);
        }
    }






}

