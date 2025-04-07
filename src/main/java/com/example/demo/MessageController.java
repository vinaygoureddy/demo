package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        String message = "Hello World";
        return ResponseEntity.ok(message);
    }
}