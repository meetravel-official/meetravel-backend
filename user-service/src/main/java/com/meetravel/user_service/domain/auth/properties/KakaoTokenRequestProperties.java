package com.meetravel.user_service.domain.auth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oauth.kakao")
public class KakaoTokenRequestProperties {

    private String grantType;
    private String clientId;
    private String redirectUri;
    private String clientSecret;
    private String scope;
    private String contentType;

}
