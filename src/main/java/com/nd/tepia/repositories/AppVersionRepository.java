package com.nd.tepia.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nd.tepia.entities.AppVersion;

public interface AppVersionRepository extends MongoRepository<AppVersion, Long>{
       
}
