package com.chat.app.chatapp.controller;

import com.chat.app.chatapp.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/public")
    public ResponseEntity<MessageDto> home() {
        return ResponseEntity.ok(new MessageDto("public content"));
    }
}
