package com.chat.app.chatapp.web.controller;

import com.chat.app.chatapp.web.dto.MessageDto;
import com.chat.app.chatapp.web.dto.UserDto;
import com.chat.app.chatapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PrivateController {

    private UserService userService;

    public PrivateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/messages")
    public ResponseEntity<UserDto> home(
            @AuthenticationPrincipal(expression = "name") String username,
            @AuthenticationPrincipal(expression = "attributes") Map<String, Object> attributes) {
        return ResponseEntity.ok(userService.authenticatedUser(attributes));
    }

    @GetMapping("/test")
    public ResponseEntity<MessageDto> message() {
        MessageDto messageDto = MessageDto.builder().id("123").build();
        return ResponseEntity.ok(messageDto);
    }

}
