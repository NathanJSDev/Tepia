package com.nd.tepia.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.nio.charset.StandardCharsets;
import com.nd.tepia.TepiaApplication;

@RestController
@RequestMapping(value = "/*")
public class ErrorResource {
    
    @GetMapping
    public String showError() {
        try (BufferedInputStream o = (BufferedInputStream)TepiaApplication.class.getResource(String.format("views/%d.html", 404)).getContent()){
            return new String(o.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
