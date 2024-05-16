package com.nd.tepia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nd.tepia.entities.properties.UserProperties;

public interface UserPropertiesRepository extends JpaRepository<UserProperties, Long>{
    
    
}
