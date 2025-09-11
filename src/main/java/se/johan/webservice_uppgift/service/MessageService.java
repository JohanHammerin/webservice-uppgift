package se.johan.webservice_uppgift.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.model.Message;
import se.johan.webservice_uppgift.repository.ChatUserRepository;
import se.johan.webservice_uppgift.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageService {

    private final ChatUserRepository chatUserRepository;
    private final MessageRepository messageRepository;
    private final PasswordEncoder passwordEncoder;

    public MessageService(ChatUserRepository chatUserRepository, MessageRepository messageRepository, PasswordEncoder passwordEncoder) {
        this.chatUserRepository = chatUserRepository;
        this.messageRepository = messageRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Message sendMessage(String username, String rawPassword, String body, String receiver) {
        ChatUser senderCheck = chatUserRepository.findByUsername(username);
        ChatUser receiverCheck = chatUserRepository.findByUsername(receiver);

        if (senderCheck == null || !passwordEncoder.matches(rawPassword, senderCheck.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        if (receiverCheck == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver not found");
        }

        Message message = new Message();
        message.setSender(senderCheck.getUsername());
        message.setReceiver(receiver);
        message.setBody(body);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }
}

