package com.meetravel.user_service.domain.auth.service;


import com.meetravel.user_service.domain.auth.dto.response.KakaoToken;
import com.meetravel.user_service.domain.auth.dto.response.LoginResponse;
import com.meetravel.user_service.domain.auth.properties.KakaoTokenRequestProperties;
import com.meetravel.user_service.domain.user.enums.SocialType;
import com.meetravel.user_service.domain.user.repository.UserRepository;
import com.meetravel.user_service.global.jwt.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final KakaoTokenRequestProperties kakaoTokenRequestProperties;
    private final KakaoAuthFeignClient kakaoAuthFeignClient;
    private final JwtService jwtService;

    /**
     * 카카오 로그인/JWT 발급
     *
     * @param authorizationCode
     * @param response
     * @return
     */
    @Transactional
    public LoginResponse kakaoLogin(String authorizationCode, HttpServletResponse response) {

        // 1. 인가 코드로 카카오 서버에 카카오 토큰 요청 API 호출
        KakaoToken kakaoToken = this.getKakaoToken(authorizationCode);

        // 2. ID 토큰 정보 받아오기
        KakaoToken.IdToken idToken = this.getKakaoIdToken(kakaoToken.getIdToken());

        // 3. 받아온 ID 토큰으로부터 카카오 회원 고유번호 추출, Dto 회원 여부 true, 임시토큰 여부 false 설정(default)
        String userId = idToken.getSub() + SocialType.KAKAO.getSocialSuffix();
        boolean isRegisteredUser = true;
        boolean isTemporaryToken = false;


        /**
         * 기존 회원이 아닌 경우, 회원가입 대상 -> isUser 구분 값 false 설정 및 임시토큰 발급 대상 설정
         */
        if (!userRepository.findByUserId(userId).isPresent()) {
            isRegisteredUser = false;
            isTemporaryToken = true;
        }

        // 4. JWT 생성 및 헤더에 싣기
        LoginResponse loginResponse = jwtService.createJwtToken(userId, isTemporaryToken);
        jwtService.setHeaderAccessToken(response, loginResponse.getAccessToken());
        jwtService.setHeaderRefreshToken(response, loginResponse.getRefreshToken());

        // 로그인 응답
        return LoginResponse.builder()
                .userId(userId)
                .grantType(loginResponse.getGrantType())
                .accessToken(loginResponse.getAccessToken())
                .accessTokenExpiresIn(loginResponse.getAccessTokenExpiresIn())
                .refreshToken(loginResponse.getRefreshToken())
                .refreshTokenExpiresIn(loginResponse.getRefreshTokenExpiresIn())
                .socialType(SocialType.KAKAO)
                .isRegisteredUser(isRegisteredUser)
                .build();
    }

    /**
     * 카카오 토큰 받아오기
     *
     * @param authorizationCode
     * @return
     */
    private KakaoToken getKakaoToken(String authorizationCode) {
        MultiValueMap<String, String> kakaoTokenRequestBody = new LinkedMultiValueMap<>();
        kakaoTokenRequestBody.add("grant_type", kakaoTokenRequestProperties.getGrantType());
        kakaoTokenRequestBody.add("client_id", kakaoTokenRequestProperties.getClientId());
        kakaoTokenRequestBody.add("redirect_uri", kakaoTokenRequestProperties.getRedirectUri());
        kakaoTokenRequestBody.add("client_secret", kakaoTokenRequestProperties.getClientSecret());
        kakaoTokenRequestBody.add("code", authorizationCode);

        return kakaoAuthFeignClient.getKakaoToken(kakaoTokenRequestBody);
    }

    /**
     * 카카오 ID토큰 정보 받아오기
     *
     * @param idToken
     * @return
     */
    private KakaoToken.IdToken getKakaoIdToken(String idToken) {
        return kakaoAuthFeignClient.getIdTokenInfo(idToken);
    }


}
