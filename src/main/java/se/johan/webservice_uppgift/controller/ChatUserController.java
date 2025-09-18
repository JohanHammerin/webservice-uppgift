package se.johan.webservice_uppgift.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.johan.webservice_uppgift.dto.AddFriendDTO;
import se.johan.webservice_uppgift.dto.RegisterDTO;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.repository.ChatUserRepository;
import se.johan.webservice_uppgift.service.ChatUserService;


import java.util.List;

@RestController
@RequestMapping("/chatUser")
public class ChatUserController {


    private final ChatUserService service;
    private final ChatUserRepository chatUserRepository;


    public ChatUserController(ChatUserService service, ChatUserRepository chatUserRepository) {
        this.service = service;
        this.chatUserRepository = chatUserRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<ChatUser> register(@Valid @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registerUser(registerDTO));
    }

    @PutMapping("/addFriend")
    public ResponseEntity<ChatUser> addFriend(@Valid @RequestBody AddFriendDTO addFriendDTO) {
        ChatUser updatedUser = service.addFriendService(addFriendDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/getFriends")
    public ResponseEntity<List<String>> getFriends(@Valid @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(service.getFriendsService(registerDTO));
    }

    @GetMapping("/discover")
    public ResponseEntity<List<String>> discoverUsers(@Valid @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(service.discoverService(registerDTO));
    }

}
