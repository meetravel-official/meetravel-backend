package com.meetravel.review_service.domain.review.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TravelMateReview {
    GOOD("좋았어요"), BAD("별로였어요");

    private final String desc;
}
