package com.chat.app.chatapp.web.controller;

import com.chat.app.chatapp.web.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/public/messages")
    public ResponseEntity<MessageDto> home() {
        MessageDto messageDto = MessageDto.builder().message("Public Content").build();
        return ResponseEntity.ok(messageDto);
    }
}
