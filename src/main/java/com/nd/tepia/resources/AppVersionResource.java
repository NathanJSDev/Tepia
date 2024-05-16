package com.nd.tepia.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.nd.tepia.entities.AppVersion;
import com.nd.tepia.services.AppVersionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/app_versions")
public class AppVersionResource {
    
    @Autowired
    private AppVersionService service;

    @GetMapping
    public ResponseEntity<List<AppVersion>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
    

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<AppVersion> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
    
}
