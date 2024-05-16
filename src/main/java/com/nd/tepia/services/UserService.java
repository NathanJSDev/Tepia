package com.nd.tepia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.tepia.entities.User;
import com.nd.tepia.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        return repository.findById(id).get();
    }

    public User findByByname(String byname){
        return repository.findByByname(byname);
    }

    public User findByEmail(String email){
        return repository.findByEmail(email);
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public boolean existsByByname(String byname){
        return repository.existsByByname(byname);
    }

    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }

    public boolean existsById(Long id){
        return repository.existsById(id);
    }
}
