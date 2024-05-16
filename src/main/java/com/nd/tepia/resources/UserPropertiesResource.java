package com.nd.tepia.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.nd.tepia.entities.properties.UserProperties;
import com.nd.tepia.services.UserPropertiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/user_properties")
public class UserPropertiesResource {

    @Autowired
    private UserPropertiesService service;

    @GetMapping
    public ResponseEntity<List<UserProperties>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserProperties> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

}
