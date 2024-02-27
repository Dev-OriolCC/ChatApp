package com.chat.app.chatapp.dto;

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
