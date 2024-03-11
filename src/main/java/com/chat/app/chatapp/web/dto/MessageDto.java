package com.chat.app.chatapp.web.dto;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    String id;
    String username;
    String name;
    String message;
    String picture;
    Boolean email_verified;

    public MessageDto(String message) {
        this.message = message;
    }
}
