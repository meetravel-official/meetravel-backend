package com.meetravel.user_service.domain.user.repository;

import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.user_service.domain.user.dto.response.GetMyPageResponse;
import com.meetravel.user_service.domain.user.enums.Review;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.meetravel.user_service.domain.user.entity.QReviewEntity.reviewEntity;
import static com.meetravel.user_service.domain.user.entity.QUserPrefTravelDestEntity.userPrefTravelDestEntity;
import static com.meetravel.user_service.domain.user.entity.QUserReviewEntity.userReviewEntity;

@Repository
@RequiredArgsConstructor
public class UserReviewRepositoryImpl implements UserReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public List<Review> getUserReview(String userId) {
        return queryFactory.select(reviewEntity.review)
                .from(userReviewEntity)
                .join(userReviewEntity.review, reviewEntity).fetchJoin()
                .where(userIdEq(userId))
                .groupBy(reviewEntity.id)
                .orderBy(userReviewEntity.count().desc())
                .fetch();
    }

    private BooleanExpression userIdEq(String userId) {
        if (userId == null) {
            return null;
        }
        return userReviewEntity.user.userId.eq(userId);
    }

}
