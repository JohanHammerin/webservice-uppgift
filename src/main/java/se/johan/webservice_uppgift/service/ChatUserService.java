package se.johan.webservice_uppgift.service;


import com.mongodb.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.webservice_uppgift.dto.RegisterRequest;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.repository.ChatUserRepository;

@Service
public class ChatUserService {
    private final ChatUserRepository chatUserRepository;
    private final PasswordEncoder passwordEncoder;


    public ChatUserService(ChatUserRepository chatUserRepository, PasswordEncoder passwordEncoder) {
        this.chatUserRepository = chatUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Kollar först om det finns en användare med samma namn i databasen

    public ChatUser registerUser(RegisterRequest registerRequest) {
        if (chatUserRepository.findByUsername(registerRequest.getUsername()) != null) {
            throw new IllegalArgumentException("Username already taken");
        }

        //Skapar ny ChatUser, sätter lösenord och username och hashar lösenord innan det sparas med .encode

        ChatUser u = new ChatUser();
        u.setUsername(registerRequest.getUsername());
        u.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        try {
            //sparar användaren
            return chatUserRepository.save(u);

            //Dubbelkollar att 2 användare inte skrivit in samma namn samtidigt
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Username already taken");
        }


    }
}











