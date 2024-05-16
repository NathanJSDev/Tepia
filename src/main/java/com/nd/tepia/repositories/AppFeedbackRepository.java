package com.nd.tepia.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nd.tepia.entities.AppFeedback;
import com.nd.tepia.entities.PK.AppFeedbackPK;

public interface AppFeedbackRepository extends MongoRepository<AppFeedback, AppFeedbackPK>{
}