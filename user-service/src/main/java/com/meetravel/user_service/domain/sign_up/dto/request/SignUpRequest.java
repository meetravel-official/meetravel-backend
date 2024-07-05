package com.meetravel.user_service.domain.sign_up.dto.request;

import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.user_service.domain.user.enums.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Set;

@Getter
public class SignUpRequest {

    @NotBlank
    private String userId;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String profileImage;
    private TravelFrequency travelFrequency;
    private ScheduleType scheduleType;
    private PlanningType planningType;
    private String mbti;
    private String hobby;
    private String intro;
    private SocialType socialType;
    private Set<TravelDest> userTravelDestinations;

}
