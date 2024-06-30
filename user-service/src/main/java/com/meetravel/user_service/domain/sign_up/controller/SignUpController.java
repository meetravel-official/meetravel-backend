package com.meetravel.user_service.domain.sign_up.controller;

import com.meetravel.user_service.domain.sign_up.dto.request.SignUpRequest;
import com.meetravel.user_service.domain.sign_up.service.SignUpService;
import com.meetravel.user_service.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController implements SignUpControllerDoc {

    private final UserService userService;
    private final SignUpService signUpService;

    @PostMapping("")
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        log.info("회원가입");
        signUpService.signUp(signUpRequest);
    }
}
