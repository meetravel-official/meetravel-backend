package com.meetravel.review_service.domain.review.repository;

import com.meetravel.review_service.domain.review.entity.TravelMateReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TravelMateReviewRepository extends JpaRepository<TravelMateReviewEntity, Long> {

}
