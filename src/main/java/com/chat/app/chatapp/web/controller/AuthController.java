package com.chat.app.chatapp.web.controller;

import com.chat.app.chatapp.web.dto.TokenDto;
import com.chat.app.chatapp.web.dto.UrlDto;
import com.chat.app.chatapp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/url")
    public ResponseEntity<UrlDto> auth() {
        UrlDto urlDto = UrlDto.builder().url(authService.getUrl()).build();
        return ResponseEntity.ok(urlDto);
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenDto> callback(@RequestParam("code") String code) {
        String token = authService.getToken(code);
        return ResponseEntity.ok(new TokenDto(token));

    }


}
