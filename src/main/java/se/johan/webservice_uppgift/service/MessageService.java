package se.johan.webservice_uppgift.service;

import org.springframework.stereotype.Service;
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

    public MessageService(ChatUserRepository chatUserRepository, MessageRepository messageRepository) {
        this.chatUserRepository = chatUserRepository;
        this.messageRepository = messageRepository;
    }
    public Optional<Message> sendMessage(String username, String password, String body, String receiver) {
        return Optional.ofNullable(chatUserRepository.findByUsername(username))
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    Message message = new Message();
                    message.setSender(user.getUsername());
                    message.setBody(body);
                    message.setReceiver(receiver);
                    message.setTimestamp(LocalDateTime.now());
                    return messageRepository.save(message);
                });
    }


}

