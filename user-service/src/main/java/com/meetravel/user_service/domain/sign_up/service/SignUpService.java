package com.meetravel.user_service.domain.sign_up.service;

import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.module_common.exception.BadRequestException;
import com.meetravel.module_common.exception.ErrorCode;
import com.meetravel.user_service.domain.sign_up.dto.request.SignUpRequest;
import com.meetravel.user_service.domain.sign_up.dto.response.GetSignUpInfoList;
import com.meetravel.user_service.domain.travel_destination.entity.TravelDestEntity;
import com.meetravel.user_service.domain.travel_destination.repository.TravelDestRepository;
import com.meetravel.user_service.domain.user.entity.UserEntity;
import com.meetravel.user_service.domain.user.entity.UserPrefTravelDestEntity;
import com.meetravel.user_service.domain.user.enums.*;
import com.meetravel.user_service.domain.user.repository.UserPrefTravelDestRepository;
import com.meetravel.user_service.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final UserPrefTravelDestRepository userPrefTravelDestRepository;
    private final TravelDestRepository travelDestRepository;

    /**
     * 회원가입
     *
     * @param signUpRequest
     */
    @Transactional
    public void signUp(SignUpRequest signUpRequest) {

        // 이미 존재하는 회원 ID인지 검증
        if (userRepository.findByUserId(signUpRequest.getUserId()).isPresent()) {
            throw new BadRequestException(ErrorCode.ALREADY_EXISTS_USER_ID);
        }

        // 유저 엔티티 생성
        UserEntity userEntity = UserEntity.builder()
                .userId(signUpRequest.getUserId())
                .name(signUpRequest.getName())
                .nickName(signUpRequest.getNickName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .profileImage(signUpRequest.getProfileImage())
                .travelFrequency(signUpRequest.getTravelFrequency())
                .scheduleType(signUpRequest.getScheduleType())
                .planningType(signUpRequest.getPlanningType())
                .mbti(signUpRequest.getMbti())
                .hobby(signUpRequest.getHobby())
                .intro(signUpRequest.getIntro())
                .socialType(signUpRequest.getSocialType())
                .role(Role.USER)
                .build();

        // 회원 선호여행지 추가
        this.addPrefTravelDestination(signUpRequest.getUserTravelDestinations(), userEntity);

        // 회원가입
        userRepository.save(userEntity);

    }

    /**
     * 회원의 선호 여행지 추가
     * @param userTravelDestinations
     * @param userEntity
     */
    private void addPrefTravelDestination(Set<TravelDest> userTravelDestinations, UserEntity userEntity) {

        for (TravelDest travelDestination : userTravelDestinations) {
            TravelDestEntity travelDest = travelDestRepository.findByTravelDestId(travelDestination)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.DATA_VALIDATION_ERROR));

            // 중간 테이블 객체 생성
            UserPrefTravelDestEntity userPrefTravelDest = UserPrefTravelDestEntity.builder()
                    .userEntity(userEntity)
                    .travelDestEntity(travelDest)
                    .build();

            // 회원 선호 여행지 DB 저장
            userPrefTravelDestRepository.save(userPrefTravelDest);

            // 각 객체로 불러올 수 있도록 리스트에 담아줌
            userEntity.addUserPrefTravelDest(userPrefTravelDest);
            travelDest.addUserPrefTravelDest(userPrefTravelDest);
        }

    }

    /**
     * 회원가입 시 필요한 목록 조회
     * @return
     */
    @Transactional(readOnly = true)
    public GetSignUpInfoList getSignUpInfoList() {

        // 여행 횟수 
        List<GetSignUpInfoList.TravelFrequencyInfo> travelFrequencies= this.getTravelFrequencyInfo();
        
        // 여행 취향 
        List<GetSignUpInfoList.ScheduleTypeInfo> scheduleTypes = this.getScheduleTypeInfo();
        List<GetSignUpInfoList.PlanningTypeInfo> planningTypes = this.getPlanningTypeInfo();
        
        // 선호 여행지
        List<GetSignUpInfoList.TravelDestInfo> travelDestInfoList = this.getTravelDestInfo();

        return GetSignUpInfoList.builder()
                .travelFrequencies(travelFrequencies)
                .scheduleTypes(scheduleTypes)
                .planningTypes(planningTypes)
                .travelDestInfoList(travelDestInfoList)
                .build();
    }


    /**
     * 여행 횟수 목록 가져오기
     * @return
     */
    private List<GetSignUpInfoList.TravelFrequencyInfo> getTravelFrequencyInfo() {
        return Arrays.stream(TravelFrequency.values())
                .map(frequency -> GetSignUpInfoList.TravelFrequencyInfo.builder()
                        .travelFrequency(frequency)
                        .desc(frequency.getDesc())
                        .build())
                .toList();
    }

    /**
     * 여행 취향 1 목록 가져오기
     * @return
     */
    private List<GetSignUpInfoList.ScheduleTypeInfo> getScheduleTypeInfo() {
        return Arrays.stream(ScheduleType.values())
                .map(type -> GetSignUpInfoList.ScheduleTypeInfo.builder()
                        .scheduleType(type)
                        .desc(type.getDesc())
                        .build())
                .toList();
    }

    /**
     * 여행 취향 2 목록 가져오기
     * @return
     */
    private List<GetSignUpInfoList.PlanningTypeInfo> getPlanningTypeInfo() {
        return Arrays.stream(PlanningType.values())
                .map(type -> GetSignUpInfoList.PlanningTypeInfo.builder()
                        .planningType(type)
                        .desc(type.getDesc())
                        .build())
                .toList();
    }

    /**
     * 선호 여행지 목록 조회
     * @return
     */
    @Transactional(readOnly = true)
    private List<GetSignUpInfoList.TravelDestInfo> getTravelDestInfo() {
        List<TravelDestEntity> travelDestList = travelDestRepository.findAll();

        return travelDestList.stream()
                .map(travelDest -> GetSignUpInfoList.TravelDestInfo.builder()
                        .travelDestId(travelDest.getTravelDestId())
                        .destName(travelDest.getTravelDestName())
                        .build())
                .toList();
    }


}
