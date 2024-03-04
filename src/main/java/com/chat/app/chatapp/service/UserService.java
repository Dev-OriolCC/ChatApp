package com.chat.app.chatapp.service;

import com.chat.app.chatapp.dto.MessageDto;
import com.chat.app.chatapp.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    public UserDto authenticatedUser(Map<String, Object> attributes) {
        UserDto userDto = UserDto.builder()
                .id((String) attributes.get("sub"))
                .username((String) attributes.get("username"))
                .name((String) attributes.get("given_name"))
                .picture((String) attributes.get("picture"))
                .email_verified((Boolean) attributes.get("email_verified"))
                .build();

        return userDto;
    }

}
