package com.mir00r.salarydisburse.config.security.oauth;

import com.mir00r.salarydisburse.domains.users.models.entities.UserAuth;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserAuth user = (UserAuth) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("id", user.getId());
        additionalInfo.put("name", user.getName());
//        additionalInfo.put("first_name", user.getFirstName());
//        additionalInfo.put("last_name", user.getLastName());
        additionalInfo.put("username", user.getUsername());

        if (user.getPhone() != null)
            additionalInfo.put("phone", user.getPhone());
        if (user.getEmail() != null)
            additionalInfo.put("email", user.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return super.enhance(accessToken, authentication);
    }


}
