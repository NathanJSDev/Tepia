package com.nd.tepia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.tepia.entities.properties.UserProperties;
import com.nd.tepia.repositories.UserPropertiesRepository;

@Service
public class UserPropertiesService {
    
    @Autowired
    private UserPropertiesRepository repository;

    public List<UserProperties> findAll(){
        return repository.findAll();
    }

    public UserProperties findById(Long id){
        return repository.findById(id).get();
    }
}
