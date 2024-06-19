package com.meetravel.user_service.domain.auth.service;


import com.meetravel.user_service.domain.auth.dto.request.SignUpRequest;
import com.meetravel.user_service.domain.auth.dto.response.KakaoToken;
import com.meetravel.user_service.domain.auth.dto.response.LoginResponse;
import com.meetravel.user_service.domain.auth.properties.KakaoTokenRequestProperties;
import com.meetravel.user_service.domain.user.entity.UserEntity;
import com.meetravel.user_service.domain.user.enums.Role;
import com.meetravel.user_service.domain.user.enums.SocialType;
import com.meetravel.user_service.domain.user.repository.UserRepository;
import com.meetravel.user_service.global.exception.BadRequestException;
import com.meetravel.user_service.global.exception.ErrorCode;
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

        // 3. 받아온 ID 토큰으로부터 카카오 회원 고유번호 추출, Dto 회원 여부 N 설정
        String userId = idToken.getSub() + SocialType.KAKAO.getSocialSuffix();
        String userYn = "N";
        
        // 4. JWT 생성 및 헤더에 싣기
        LoginResponse loginResponse = jwtService.createJwtToken(userId);
        jwtService.setHeaderAccessToken(response, loginResponse.getAccessToken());
        jwtService.setHeaderRefreshToken(response, loginResponse.getRefreshToken());

        // 회원이면 회원여부를 Y로 설정
        if (userRepository.findByUserId(userId).isPresent()) {
            userYn = "Y";
        }

        // 로그인 응답
        return LoginResponse.builder()
                .userId(userId)
                .grantType(loginResponse.getGrantType())
                .accessToken(loginResponse.getAccessToken())
                .accessTokenExpiresIn(loginResponse.getAccessTokenExpiresIn())
                .refreshToken(loginResponse.getRefreshToken())
                .refreshTokenExpiresIn(loginResponse.getRefreshTokenExpiresIn())
                .socialType(SocialType.KAKAO)
                .userYn(userYn)
                .build();
    }

    /**
     * 카카오 토큰 받아오기
     * @param authorizationCode
     * @return
     */
    private KakaoToken getKakaoToken(String authorizationCode) {
        MultiValueMap<String,String> kakaoTokenRequestBody = new LinkedMultiValueMap<>();
        kakaoTokenRequestBody.add("grant_type", kakaoTokenRequestProperties.getGrantType());
        kakaoTokenRequestBody.add("client_id", kakaoTokenRequestProperties.getClientId());
        kakaoTokenRequestBody.add("redirect_uri", kakaoTokenRequestProperties.getRedirectUri());
        kakaoTokenRequestBody.add("client_secret", kakaoTokenRequestProperties.getClientSecret());
        kakaoTokenRequestBody.add("code", authorizationCode);

        return kakaoAuthFeignClient.getKakaoToken(kakaoTokenRequestBody);
    }

    /**
     * 카카오 ID토큰 정보 받아오기
     * @param idToken
     * @return
     */
    private KakaoToken.IdToken getKakaoIdToken(String idToken) {
        return kakaoAuthFeignClient.getIdTokenInfo(idToken);
    }

    /**
     * 회원가입
     * @param signUpRequest
     */
    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        
        // 이미 존재하는 회원 ID인지 검증
        if (userRepository.findByUserId(signUpRequest.getUserId()).isPresent()) {
            throw new BadRequestException(ErrorCode.ALREADY_EXISTS_MEMBER_ID);
        }

        UserEntity userEntity = UserEntity.builder()
                .userId(signUpRequest.getUserId())
                .name(signUpRequest.getName())
                .nickName(signUpRequest.getNickName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .profileImage(signUpRequest.getProfileImage())
                .travelCount(signUpRequest.getTravelCount())
                .travelStyle1(signUpRequest.getTravelStyle1())
                .travelStyle2(signUpRequest.getTravelStyle2())
                .mbti(signUpRequest.getMbti())
                .hobby(signUpRequest.getHobby())
                .intro(signUpRequest.getIntro())
                .socialType(signUpRequest.getSocialType())
                .role(Role.USER)
                .build();

        userRepository.save(userEntity);

    }


}
