package com.meetravel.user_service.domain.user.service;

import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.module_common.exception.ErrorCode;
import com.meetravel.module_common.exception.NotFoundException;
import com.meetravel.user_service.domain.token.service.RefreshTokenService;
import com.meetravel.user_service.domain.user.dto.response.GetMyPageResponse;
import com.meetravel.user_service.domain.user.entity.UserEntity;
import com.meetravel.user_service.domain.user.enums.Review;
import com.meetravel.user_service.domain.user.repository.UserPrefTravelDestRepository;
import com.meetravel.user_service.domain.user.repository.UserRepository;
import com.meetravel.user_service.domain.user.repository.UserReviewRepository;
import com.meetravel.user_service.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final UserReviewRepository userReviewRepository;
    private final UserPrefTravelDestRepository userPrefTravelDestRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public GetMyPageResponse getMyPage(String userId) {

        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        List<Review> reviewList = userReviewRepository.getUserReview(userId);
        List<TravelDest> userPrefTravelDestList = userPrefTravelDestRepository.getUserPrefTravelDestList(userId);

        return GetMyPageResponse.builder()
                .nickname(user.getNickname())
                .planningType(user.getPlanningType())
                .hobby(user.getHobby())
                .mbti(user.getMbti())
                .intro(user.getIntro())
                .reviewList(reviewList)
                .userPrefTravelDestList(userPrefTravelDestList)
                .build();
    }

    /**
     * 회원 로그아웃
     *
     * @param refreshToken
     */
    @Transactional
    public void logout(String refreshToken) {
        // refreshToken DB에서 삭제
        refreshTokenService.deleteRefreshToken(jwtService.getUserId(refreshToken));

    }

}
