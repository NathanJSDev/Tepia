package com.nd.tepia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nd.tepia.entities.App;
import com.nd.tepia.entities.User;

import java.util.List;


public interface AppRepository extends JpaRepository<App, Long>{
    List<App> findByName(String name);
    List<App> findByCreator(User creator);
    List<App> findByLanguagesSupport(List<Double> languagesSupport);
    List<App> findByPrice(Double price);

    boolean existsByPrice(Double price);
    boolean existsByCreator(User creator);
    boolean existsByName(String name);
}
