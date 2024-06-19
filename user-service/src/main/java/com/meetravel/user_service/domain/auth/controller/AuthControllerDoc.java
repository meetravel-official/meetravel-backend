package com.meetravel.user_service.domain.auth.controller;

import com.meetravel.user_service.domain.auth.dto.request.SignUpRequest;
import com.meetravel.user_service.domain.auth.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name =  "인증/인가 - 로그인 API")
public interface AuthControllerDoc {

    @Operation(summary = "카카오 로그인", description = "카카오 로그인을 진행합니다.")
    ResponseEntity<LoginResponse> kakaoLogin(@RequestParam String authorizationCode, HttpServletResponse response);
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    void signUp(@RequestBody @Valid SignUpRequest signUpRequest);


}
