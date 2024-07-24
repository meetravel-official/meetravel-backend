package com.meetravel.review_service.domain.review.controller;

import com.meetravel.review_service.domain.review.dto.response.GetUserReviewResponse;
import com.meetravel.review_service.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDoc {

    private final ReviewService reviewService;


    @Override
    @GetMapping("/{userId}")
    public GetUserReviewResponse getUserReview(String userId) {
        return null;
    }
}
