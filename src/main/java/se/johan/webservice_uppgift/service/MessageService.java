package se.johan.webservice_uppgift.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.webservice_uppgift.dto.MessageDTO;
import se.johan.webservice_uppgift.model.ChatUser;
import se.johan.webservice_uppgift.model.Message;
import se.johan.webservice_uppgift.repository.ChatUserRepository;
import se.johan.webservice_uppgift.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<Message> sendMessage(String username, String rawPassword, String body, String receiver) {
        return Optional.ofNullable(chatUserRepository.findByUsername(username))
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword())) // jämför hash
                .map(user -> {
                    Message message = new Message();
                    message.setSender(user.getUsername());
                    message.setBody(body);
                    message.setReceiver(receiver);
                    message.setTimestamp(LocalDateTime.now());
                    return messageRepository.save(message);
                });
    }


    public List<MessageDTO> viewMessages(String username, String password) {
        ChatUser user = chatUserRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            List<Message> messages = messageRepository.findByReceiverOrderByTimestampDesc(user.getUsername());
            return messages.stream()
                    .map(m -> new MessageDTO(m.getSender(), m.getBody(), m.getTimestamp()))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }




}

