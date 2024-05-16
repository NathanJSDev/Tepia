package com.nd.tepia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nd.tepia.entities.AppVersion;

public interface AppVersionRepository extends JpaRepository<AppVersion, Long>{
       
}
