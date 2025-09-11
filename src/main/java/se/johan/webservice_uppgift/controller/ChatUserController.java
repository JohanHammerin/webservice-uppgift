package se.johan.webservice_uppgift.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.repository.ChatUserRepository;
import se.johan.webservice_uppgift.service.ChatUserService;


import java.util.List;

@RestController
@RequestMapping("/chatUser")
public class ChatUserController {
  
  
  private final ChatUserService service;


    //DTO
    public static class RegisterRequest {
        public String username;
        public String password;


    }
    @PostMapping("/register")
    public ChatUser register (@RequestBody RegisterRequest req){
        return service.registerUser(req.username, req.password);
    }


    private final ChatUserRepository chatUserRepository;

    public ChatUserController(ChatUserService service, ChatUserRepository chatUserRepository) {
        this.service = service;
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

    @GetMapping("/getFriendsTest")
    public ResponseEntity<List<String>> getFriends(@RequestBody ChatUser user) {
        ChatUser chatUser = chatUserRepository.findByUsername(user.getUsername());

        return ResponseEntity.ok(chatUser.getFriendList());
    }
}
