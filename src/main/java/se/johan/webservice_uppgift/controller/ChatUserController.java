package se.johan.webservice_uppgift.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.repository.ChatUserRepository;
import se.johan.webservice_uppgift.service.MessageService;

@RestController
@RequestMapping("/chatUser")
public class ChatUserController {


    private final ChatUserRepository chatUserRepository;

    public ChatUserController(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    @PostMapping("/createUserTest")
    public ResponseEntity<ChatUser> createUserTest(@RequestBody ChatUser user) {

        return ResponseEntity.ok(chatUserRepository.save(user));
    }

    @PutMapping("/addFriendTest")
    public ResponseEntity<ChatUser> addFriendTest(@RequestBody ChatUser user) {
        ChatUser chatUser = chatUserRepository.findByUsername(user.getUsername());

        chatUser.getFriendList().addAll(user.getFriendList());

        return ResponseEntity.ok(chatUserRepository.save(chatUser));
    }
}
