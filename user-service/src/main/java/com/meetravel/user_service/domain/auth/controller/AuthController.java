package com.meetravel.user_service.domain.auth.controller;

import com.meetravel.user_service.domain.auth.dto.request.SignUpRequest;
import com.meetravel.user_service.domain.auth.dto.response.LoginResponse;
import com.meetravel.user_service.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthControllerDoc {

    private final AuthService authService;

    @PostMapping("/kakao/login")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestParam String authorizationCode, HttpServletResponse response) {
        log.info("카카오 로그인");
        return ResponseEntity.ok(authService.kakaoLogin(authorizationCode,response));
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        log.info("회원가입");
        authService.signUp(signUpRequest);
    }

}
