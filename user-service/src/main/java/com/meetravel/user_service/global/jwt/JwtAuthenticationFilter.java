package com.meetravel.user_service.global.jwt;


import com.meetravel.user_service.domain.auth.dto.response.LoginResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    /**
     * 임시 토큰 URL
     */
    public static final String[] TEMPORARY_TOKEN_ALLOWED_URLS = {
            "/signup"
    };

    /**
     * JWT Filter 메소드
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = jwtService.getAccessToken(request);

        /** AT가 null 이 아닌 경우 */
        if (accessToken != null && jwtService.validateToken(accessToken, request)) { // 1. 토큰이 헤더에 실려왔는지, 토큰이 유효한 토큰인지 확인
            boolean isTemporary = jwtService.getIsTemporary(accessToken);

            if (isTemporary) {
                if (isNotTemporaryTokenAllowedUrl(request.getRequestURI())) { // 나머지 URL은 임시 토큰으로 접근 불가, 임시 토큰인 경우 회원가입 요청만 허용
                    return; // CustomAuthenticationEntryPoint가 처리하도록 함
                }
            }
            // accessToken이 유효하면 Context에 Authentication 저장 (임시/정식 모두)
            this.setAuthentication(accessToken, isTemporary);

        } else {
            /** AT가 헤더에 없거나, 만료되었거나, 유효하지 않은 경우
             * RT로 AT 재발급 및 API 요청 처리
             */
            String refreshToken = jwtService.getRefreshToken(request);

            if (refreshToken != null && jwtService.validateToken(refreshToken, request)) {
                String userId = jwtService.getUserId(refreshToken);
                boolean isTemporary = jwtService.getIsTemporary(refreshToken);
                LoginResponse loginResponse = jwtService.createJwtToken(userId, isTemporary); // AT,RT 재생성

                jwtService.setHeaderAccessToken(response, loginResponse.getAccessToken()); // AT 발급
                jwtService.setHeaderRefreshToken(response, loginResponse.getRefreshToken()); // RT 발급

                if (isTemporary) { // 임시 토큰인 경우
                    if (isNotTemporaryTokenAllowedUrl(request.getRequestURI())) { // 나머지 URL은 임시 토큰으로 접근 불가, 임시 토큰인 경우 회원가입 요청만 허용
                        return; // CustomAuthenticationEntryPoint가 처리하도록 함
                    }
                }
                // accessToken이 유효하면 Context에 Authentication 저장 (임시/정식 모두)
                this.setAuthentication(loginResponse.getAccessToken(), isTemporary);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Authentication 부여
     *
     * @param accessToken
     */
    public void setAuthentication(String accessToken, boolean isTemporary) {
        Authentication authentication = jwtService.getAuthentication(accessToken,isTemporary);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 임시토큰 허용 URL인지 체크하는 메소드
     *
     * @param requestUri
     * @return
     */
    private boolean isNotTemporaryTokenAllowedUrl(String requestUri) {
        return Arrays.stream(TEMPORARY_TOKEN_ALLOWED_URLS)
                .noneMatch(requestUri::startsWith);
    }
}
