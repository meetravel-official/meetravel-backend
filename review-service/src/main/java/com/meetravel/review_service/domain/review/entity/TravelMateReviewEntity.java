package com.meetravel.review_service.domain.review.entity;

import com.meetravel.module_common.audit.BaseEntity;

import com.meetravel.review_service.domain.review.enums.TravelMateReview;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "travel_mate_reviews")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class TravelMateReviewEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_MATE_REVIEW_ID")
    private Long id;

    @Column(name = "REVIEWER")
    private String reviewerId;

    @Column(name = "REVIEWEE")
    private String revieweeId;

    @ManyToOne
    @JoinColumn(name = "PERSONALITY_REVIEW")
    private PersonalityEntity personality;

    @Column(name = "TRAVEL_MATE_REVIEW")
    private TravelMateReview mateReview;


}
