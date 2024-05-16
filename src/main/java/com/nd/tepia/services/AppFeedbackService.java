package com.nd.tepia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.tepia.entities.App;
import com.nd.tepia.entities.AppFeedback;
import com.nd.tepia.entities.User;
import com.nd.tepia.entities.PK.AppFeedbackPK;
import com.nd.tepia.repositories.AppFeedbackRepository;
import com.nd.tepia.services.exceptions.ResourceNotFoundException;

@Service
public class AppFeedbackService {
    
    @Autowired
    private AppFeedbackRepository repository;

    public List<AppFeedback> findAll(){
        return repository.findAll();
    }

    public AppFeedback findById(AppFeedbackPK id){
        return repository.findById(id).get();
    }

    public AppFeedback findByUser(User u){
        List<AppFeedback> all = repository.findAll();
        for (AppFeedback appFeedback : all) {
            if(appFeedback.getUser().equals(u)){
                return appFeedback;
            }
        }
        throw new ResourceNotFoundException("AppFeedback");
    }

    public AppFeedback findByApp(App a){
        List<AppFeedback> all = repository.findAll();
        for (AppFeedback appFeedback : all) {
            if(appFeedback.getApp().equals(a)){
                return appFeedback;
            }
        }
        throw new ResourceNotFoundException("AppFeedback");
    }

    public void save (AppFeedback appFeedback){
        repository.save(appFeedback);
    }
}
