package com.meetravel.user_service.domain.user.repository;

import com.meetravel.user_service.domain.user.enums.Review;

import java.util.List;

public interface UserReviewRepositoryCustom {
    List<Review> getUserReview(String userId);
}
