package se.johan.webservice_uppgift.service;


import com.mongodb.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.webservice_uppgift.dto.AddFriendDTO;
import se.johan.webservice_uppgift.dto.RegisterDTO;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.repository.ChatUserRepository;
import se.johan.webservice_uppgift.repository.UsernameOnly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatUserService {
    private final ChatUserRepository chatUserRepository;
    private final PasswordEncoder passwordEncoder;


    public ChatUserService(ChatUserRepository chatUserRepository, PasswordEncoder passwordEncoder) {
        this.chatUserRepository = chatUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Kollar först om det finns en användare med samma namn i databasen

    public ChatUser registerUser(RegisterDTO registerDTO) {
        if (chatUserRepository.findByUsername(registerDTO.username()) != null) {
            throw new IllegalArgumentException("Username already taken");
        }

        //Skapar ny ChatUser, sätter lösenord och username och hashar lösenord innan det sparas med .encode

        ChatUser chatUser = new ChatUser();
        chatUser.setUsername(registerDTO.username());
        chatUser.setPassword(passwordEncoder.encode(registerDTO.password()));

        try {
            //sparar användaren
            return chatUserRepository.save(chatUser);

            //Dubbelkollar att 2 användare inte skrivit in samma namn samtidigt
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("Username already taken");
        }
    }

    public ChatUser addFriendService(AddFriendDTO addFriendDTO) {
        ChatUser chatUser = chatUserRepository.findByUsername(addFriendDTO.username());

        if (chatUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        if(!passwordEncoder.matches(addFriendDTO.password(), chatUser.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        ChatUser friend = chatUserRepository.findByUsername(addFriendDTO.friendUsername());
        if (friend == null) {
            throw new IllegalArgumentException("Friend not found");
        }

        if(!chatUser.getFriendList().contains(friend.getUsername())) {
            chatUser.getFriendList().add(friend.getUsername());
        }


        return chatUserRepository.save(chatUser);
    }

    public List<String> getFriendsService(RegisterDTO registerDTO) {
        ChatUser chatUser = chatUserRepository.findByUsername(registerDTO.username());

        if (chatUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        if(!passwordEncoder.matches(registerDTO.password(), chatUser.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        return chatUser.getFriendList();
    }

    public List<String> discoverService(RegisterDTO registerDTO) {
        ChatUser chatUser = chatUserRepository.findByUsername(registerDTO.username());

        if (chatUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        if(!passwordEncoder.matches(registerDTO.password(), chatUser.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        List<String> userDiscovered = chatUserRepository.findAllBy()
                        .stream()
                        .map(UsernameOnly::getUsername)
                        .collect(Collectors.toCollection(ArrayList::new));

        userDiscovered.remove(chatUser.getUsername());
        userDiscovered.removeAll(chatUser.getFriendList());

        Collections.shuffle(userDiscovered);
        int maxSize = 10;
        userDiscovered.subList(0, userDiscovered.size() - maxSize).clear();

        return userDiscovered;
    }


}











