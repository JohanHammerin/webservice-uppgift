package se.johan.webservice_uppgift.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.johan.webservice_uppgift.model.Message;


@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
