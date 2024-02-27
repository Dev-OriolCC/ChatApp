package com.chat.app.chatapp.controller;

import com.chat.app.chatapp.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

    @GetMapping("/private")
    public ResponseEntity<MessageDto> home(
            @AuthenticationPrincipal(expression = "name") String name) {
        return ResponseEntity.ok(new MessageDto("Private: "+name));
    }

}
