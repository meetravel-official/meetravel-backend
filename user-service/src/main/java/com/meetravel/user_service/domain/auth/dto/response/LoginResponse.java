package com.meetravel.user_service.domain.auth.dto.response;

import com.meetravel.user_service.domain.user.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String userId;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
    private Long refreshTokenExpiresIn;
    private SocialType socialType;
    private boolean isRegisteredUser;

}
