package com.meetravel.user_service.domain.user.entity;

import com.meetravel.module_common.audit.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_reviews")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class UserReviewEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_REVIEW_ID")
    private Long id;

    @Column(name = "REVIEWER_ID")
    private String reviewerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "REVIEW")
    private ReviewEntity review;


}
