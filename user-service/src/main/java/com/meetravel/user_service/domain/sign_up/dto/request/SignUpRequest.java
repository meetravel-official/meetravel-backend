package com.meetravel.user_service.domain.sign_up.dto.request;

import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.user_service.domain.user.enums.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class SignUpRequest {

    @NotBlank
    private String userId;
    private String name;
    private String nickname;
    private LocalDate birthDate;
    private String phoneNumber;
    private String profileImageUrl;
    private TravelFrequency travelFrequency;
    private ScheduleType scheduleType;
    private PlanningType planningType;
    private MBTI mbti;
    private String hobby;
    private String intro;
    private SocialType socialType;
    private Set<TravelDestInfo> userTravelDestinations;

    @Getter
    public static class TravelDestInfo {
        private Long travelDestId;
        private TravelDest travelDest;

    }

}
