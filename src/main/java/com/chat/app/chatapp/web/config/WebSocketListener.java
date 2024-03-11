package com.chat.app.chatapp.web.config;


import com.chat.app.chatapp.web.dto.ChatMessageDto;
import com.chat.app.chatapp.web.dto.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketListener {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection"+event.getUser());
    }



    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        //
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) stompHeaderAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("User disconnected: {}", username);
            var chatMessage = ChatMessageDto.builder()
                    .messageType(MessageType.LEAVE)
                    .user(username)
                    .build();
            simpMessageSendingOperations.convertAndSend("/topic",chatMessage);
        }

    }

}
