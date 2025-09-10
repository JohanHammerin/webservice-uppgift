package se.johan.webservice_uppgift.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.johan.webservice_uppgift.model.ChatUser;

@Repository
public interface ChatUserRepository extends MongoRepository<ChatUser, String> {
    ChatUser findByUsername(String username);
}
