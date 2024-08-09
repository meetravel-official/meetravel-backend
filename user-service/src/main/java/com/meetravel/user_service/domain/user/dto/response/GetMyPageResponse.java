package com.meetravel.user_service.domain.user.dto.response;

import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.user_service.domain.user.enums.MBTI;
import com.meetravel.user_service.domain.user.enums.PlanningType;
import com.meetravel.user_service.domain.user.enums.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetMyPageResponse {
    private String nickname;
    private PlanningType planningType;
    private String hobby;
    private MBTI mbti;
    private String intro;
    private List<Review> reviewList;
    private List<TravelDest> userPrefTravelDestList;

}
