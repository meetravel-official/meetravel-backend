package com.meetravel.user_service.domain.sign_up.controller;

import com.meetravel.user_service.domain.sign_up.dto.request.SignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원가입 관련 API")
public interface SignUpControllerDoc {
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    void signUp(@RequestBody @Valid SignUpRequest signUpRequest);

}
