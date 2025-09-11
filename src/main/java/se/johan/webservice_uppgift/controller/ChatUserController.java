package se.johan.webservice_uppgift.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.service.ChatUserService;

@RestController
@RequestMapping("/users")
public class ChatUserController {

    private final ChatUserService service;


    public ChatUserController(ChatUserService service) {
        this.service = service;
    }

    //DTO
    public static class RegisterRequest {
        public String username;
        public String password;


    }
    @PostMapping("/register")
    public ChatUser register (@RequestBody RegisterRequest req){
        return service.registerUser(req.username, req.password);
    }


}
