package com.meetravel.user_service.domain.auth.controller;

import com.meetravel.user_service.domain.auth.dto.response.LoginResponse;
import com.meetravel.user_service.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthControllerDoc {

    private final AuthService authService;

    @Override
    @PostMapping("/kakao/login")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestParam String authorizationCode, HttpServletResponse response) {
        log.info("카카오 로그인");
        return ResponseEntity.ok(authService.kakaoLogin(authorizationCode, response));
    }
}
