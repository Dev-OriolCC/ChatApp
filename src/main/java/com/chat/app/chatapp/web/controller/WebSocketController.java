package com.chat.app.chatapp.web.controller;


import com.chat.app.chatapp.web.dto.ChatMessageDto;
import com.chat.app.chatapp.web.dto.MessageType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessageDto chatMessage(@DestinationVariable String roomId, ChatMessageDto chatMessage) {
        return new ChatMessageDto(chatMessage.getMessage(), chatMessage.getUser(), MessageType.CHAT);
    }

    @MessageMapping("/chatUser/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessageDto chatUser(@DestinationVariable String roomId, ChatMessageDto chatMessage,
                                   SimpMessageHeaderAccessor messageHeaderAccessor) {
        messageHeaderAccessor.getSessionAttributes().put("user", chatMessage.getUser());
        System.out.println("USER: "+chatMessage.getUser());
        chatMessage.setMessageType(MessageType.JOIN);
        return chatMessage;
    }



}
