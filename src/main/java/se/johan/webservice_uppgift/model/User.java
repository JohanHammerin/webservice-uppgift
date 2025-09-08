package se.johan.webservice_uppgift.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record User (String username, String password){
}
