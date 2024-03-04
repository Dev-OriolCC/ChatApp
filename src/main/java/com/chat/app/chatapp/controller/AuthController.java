package com.chat.app.chatapp.controller;

import com.chat.app.chatapp.dto.TokenDto;
import com.chat.app.chatapp.dto.UrlDto;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.client-secret}")
    private String clientSecret;

    @GetMapping("/url")
    public ResponseEntity<UrlDto> auth() {
        String url = new GoogleAuthorizationCodeRequestUrl(
                clientId,
                "http://localhost:4200",
                Arrays.asList("email","profile","openid")
        ).build();

        return ResponseEntity.ok(new UrlDto(url));
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenDto> callback(@RequestParam("code") String code) {
        String token;
        try {
            token = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    "http://localhost:4200"
            ).execute().getAccessToken();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new TokenDto(token));

    }


}
