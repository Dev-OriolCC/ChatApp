package com.chat.app.chatapp.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDto {
    String message;
    String user;

    MessageType messageType;


}
