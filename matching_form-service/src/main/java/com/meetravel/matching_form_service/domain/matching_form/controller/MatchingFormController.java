package com.meetravel.matching_form_service.domain.matching_form.controller;

import com.meetravel.matching_form_service.domain.matching_form.dto.request.CreateMatchingFormRequest;
import com.meetravel.matching_form_service.domain.matching_form.service.MatchingFormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/matching-form")
public class MatchingFormController {

    private final MatchingFormService matchingFormService;

    @PostMapping("/{userId}")
    public void createMatchingApplicationForm(@PathVariable String userId,
                                              @RequestBody @Valid CreateMatchingFormRequest request) {
        matchingFormService.createMatchingForm(userId, request);
    }



}
