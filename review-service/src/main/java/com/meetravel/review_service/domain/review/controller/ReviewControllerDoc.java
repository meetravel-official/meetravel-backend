package com.meetravel.review_service.domain.review.controller;

import com.meetravel.review_service.domain.review.dto.response.GetUserReviewResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "여행/여행메이트 평가 API")
public interface ReviewControllerDoc {

    GetUserReviewResponse getUserReview(@PathVariable String userId);
}
