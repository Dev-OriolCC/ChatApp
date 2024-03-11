package com.chat.app.chatapp.service;

import com.chat.app.chatapp.data.entities.UserEntity;
import com.chat.app.chatapp.data.repositories.UserRepository;
import com.chat.app.chatapp.web.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto save(UserDto userDto) {
        UserEntity user = userRepository.save(userDto);
    }

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
