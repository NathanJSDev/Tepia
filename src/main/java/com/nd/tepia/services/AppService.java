package com.nd.tepia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.tepia.entities.App;
import com.nd.tepia.repositories.AppRepository;

@Service
public class AppService {
    
    @Autowired
    private AppRepository repository;

    public List<App> findAll(){
        return repository.findAll();
    }

    public App findById(Long id){
        return repository.findById(id).get();
    }

    public List<App> findByName(String name){
        return repository.findByName(name);
    }
}
