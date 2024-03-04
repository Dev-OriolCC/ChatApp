package com.chat.app.chatapp.controller;

import com.chat.app.chatapp.dto.UserDto;
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

        //System.out.println("MesageDTO: "+messageDto.toString());

        return ResponseEntity.ok(userService.authenticatedUser(attributes));
    }

}
