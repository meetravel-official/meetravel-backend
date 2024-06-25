package com.meetravel.user_service.domain.user.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TimeManagement {

    TIGHT("빠듯하게"),
    RELAXED("여유롭게");

    private final String description;
}
