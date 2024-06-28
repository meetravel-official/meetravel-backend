package com.meetravel.user_service.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanningStyle {

    PLANNED("계획적으로"),
    IMPROMPTU("즉흥적으로");

    private final String description;
}
