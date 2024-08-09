package com.meetravel.user_service.domain.user.entity;

import com.meetravel.module_common.audit.BaseEntity;
import com.meetravel.module_common.converter.TravelDestConverter;
import com.meetravel.user_service.domain.user.enums.*;
import com.meetravel.user_service.global.converter.PlanningTypeConverter;
import com.meetravel.user_service.global.converter.ScheduleTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PASSWORD")
    @Builder.Default
    private String password = ""; // Default 값으로 빈 문자열 설정

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @Column(name = "TRAVEL_COUNT")
    @Convert(converter = TravelDestConverter.class)
    private TravelFrequency travelFrequency;

    @Column(name = "SCHEDULE_TYPE")
    @Convert(converter = ScheduleTypeConverter.class)
    private ScheduleType scheduleType;

    @Column(name = "PLANNING_TYPE")
    @Convert(converter = PlanningTypeConverter.class)
    private PlanningType planningType;

    @Column(name = "MBTI")
    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @Column(name = "HOBBY")
    private String hobby;

    @Column(name = "INTRO")
    private String intro;

    @Enumerated(EnumType.STRING)
    @Column(name = "SOCIAL_TYPE")
    private SocialType socialType; // KAKAO

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRoleEntity> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<UserPrefTravelDestEntity> userPrefTravelDests = new HashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserReviewEntity> reviews = new HashSet<>();


    // 회원의 선호여행지 추가(등록)
    public void addUserPrefTravelDest(UserPrefTravelDestEntity userPrefTravelDestEntity) {
        this.userPrefTravelDests.add(userPrefTravelDestEntity);
    }

    public void addUserRole(UserRoleEntity userRole) {
        this.userRoles.add(userRole);
    }

}
