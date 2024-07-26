package com.meetravel.review_service.domain.review.service;

import com.meetravel.review_service.domain.review.dto.response.GetUserReviewResponse;
import com.meetravel.review_service.domain.review.repository.TravelMateReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final TravelMateReviewRepository travelMateReviewRepository;


    @Transactional(readOnly = true)
    public GetUserReviewResponse getUserReview(String userId) {
        return null;
    }

}
