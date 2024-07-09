package com.meetravel.user_service.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TravelFrequency {
    NONE("안가요!"),
    ONE_TO_THREE("1~3"),
    FOUR_TO_SIX("4~6"),
    SEVEN_OR_MORE("7번 이상");

    private final String desc;
}
