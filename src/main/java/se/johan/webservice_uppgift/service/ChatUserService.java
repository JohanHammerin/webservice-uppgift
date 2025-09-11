package se.johan.webservice_uppgift.service;


import com.mongodb.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.repository.ChatUserRepository;

@Service
public class ChatUserService {
    private final ChatUserRepository repo;
    private final PasswordEncoder passwordEncoder;


    public ChatUserService(ChatUserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    //Kollar först om det finns en användare med samma namn i databasen

    public ChatUser registerUser(String username, String rawPassword) {
        if (repo.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already taken");
        }

        //Skapar ny ChatUser, sätter lösenord och username och hashar lösenord innan det sparas med .encode

        ChatUser u = new ChatUser();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(rawPassword));

        try {
            //sparar användaren
            return repo.save(u);

            //Dubbelkollar att 2 användare inte skrivit in samma namn samtidigt
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Username already taken");
        }


    }
}











