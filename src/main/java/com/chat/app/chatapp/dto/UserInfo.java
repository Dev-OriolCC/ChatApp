package com.chat.app.chatapp.dto;
public record UserInfo(
        String sub,
        String name,
        String given_name,
        String family_name,
        String picture,
        boolean email_verified,
        String locale
) {

}
