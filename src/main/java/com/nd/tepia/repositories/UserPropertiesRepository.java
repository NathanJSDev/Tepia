package com.nd.tepia.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nd.tepia.entities.properties.UserProperties;

public interface UserPropertiesRepository extends MongoRepository<UserProperties, Long>{
    
    
}
