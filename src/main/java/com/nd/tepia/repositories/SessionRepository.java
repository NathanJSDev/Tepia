package com.nd.tepia.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nd.tepia.entities.Session;
import com.nd.tepia.entities.User;

import java.util.List;


public interface SessionRepository extends MongoRepository<Session, Long>{
    
    List<Session> findByActive(Boolean active);
    List<Session> findByUser(User user);
    List<Session> findBySessionKey(String SessionKey);
    boolean existsBySessionKey(String SessionKey);
}
