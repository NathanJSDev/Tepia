package com.nd.tepia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.tepia.entities.AppVersion;
import com.nd.tepia.repositories.AppVersionRepository;

@Service
public class AppVersionService {
    
    @Autowired
    private AppVersionRepository repository;

    public List<AppVersion> findAll(){
        return repository.findAll();
    }

    public AppVersion findById(Long id){
        return repository.findById(id).get();
    }
}
