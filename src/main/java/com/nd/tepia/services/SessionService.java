package com.nd.tepia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.tepia.entities.Session;
import com.nd.tepia.repositories.SessionRepository;

@Service
public class SessionService {
    
    @Autowired
    private SessionRepository repository;

    public List<Session> findAll(){
        return repository.findAll();
    }

    public Session findById(Long id){
        return repository.findById(id).get();
    }

    public Session save(Session s){
        return repository.save(s);
    }
}
