package com.nd.tepia.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.nd.tepia.entities.App;
import com.nd.tepia.services.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/apps")
public class AppResource {
    
    @Autowired
    private AppService service;

    @GetMapping
    public ResponseEntity<List<App>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
    

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<App> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
    
}
