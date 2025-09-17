package se.johan.webservice_uppgift.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                request.username(),
                request.password(),
                request.body(),
                request.receiver()
        );

        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }


    @DeleteMapping("/deleteLatest")
    public ResponseEntity<Void> deleteLatestMessage(@RequestBody SendMessageRequest request) {
        Optional<Message> deleted = messageService.deleteMessage(
                request.username(),
                request.password(),
                request.receiver()
        );

        if (deleted.isPresent()) {
            // 204 No Content, ingen body
            return ResponseEntity.noContent().build();
        } else {
            // 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/viewMessages")
    public ResponseEntity<?> viewMessages(@RequestBody ViewMessagesRequest request) {
        List<MessageDTO> messages = messageService.viewMessages(
                request.username(),
                request.password()
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

