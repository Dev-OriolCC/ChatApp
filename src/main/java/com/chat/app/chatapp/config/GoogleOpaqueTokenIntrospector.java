package com.chat.app.chatapp.config;

import com.chat.app.chatapp.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final WebClient userInfoClient;

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserInfo userInfo = userInfoClient.get()
                .uri( uriBuilder -> uriBuilder
                        .path("/oauth2/v3/userinfo")
                        .queryParam("access_token", token)
                        .build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", userInfo.sub());
        attributes.put("username", userInfo.name());
        attributes.put("given_name", userInfo.given_name());
        attributes.put("picture", userInfo.picture());
        attributes.put("email_verified", userInfo.email_verified());
        attributes.put("locale", userInfo.locale());
        //String sub,name,given_name,family_name,picture,email_verified,locale
        return new OAuth2IntrospectionAuthenticatedPrincipal(userInfo.name(), attributes, null);
    }
}
