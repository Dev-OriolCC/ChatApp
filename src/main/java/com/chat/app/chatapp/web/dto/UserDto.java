package com.chat.app.chatapp.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    String id;
    String username;
    String name;
    String message;
    String picture;
    Boolean email_verified;



}
