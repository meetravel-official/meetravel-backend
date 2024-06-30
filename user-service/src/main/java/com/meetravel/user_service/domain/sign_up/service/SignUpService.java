package com.meetravel.user_service.domain.sign_up.service;

import com.meetravel.user_service.domain.sign_up.dto.request.SignUpRequest;
import com.meetravel.user_service.domain.travel_destination.entity.TravelDestEntity;
import com.meetravel.user_service.domain.travel_destination.repository.TravelDestRepository;
import com.meetravel.user_service.domain.user.entity.UserEntity;
import com.meetravel.user_service.domain.user.entity.UserPrefTravelDestEntity;
import com.meetravel.user_service.domain.user.enums.Role;
import com.meetravel.user_service.domain.user.enums.TravelDest;
import com.meetravel.user_service.domain.user.repository.UserRepository;
import com.meetravel.user_service.global.exception.BadRequestException;
import com.meetravel.user_service.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
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
                .timeManagement(signUpRequest.getTimeManagement())
                .planningStyle(signUpRequest.getPlanningStyle())
                .mbti(signUpRequest.getMbti())
                .hobby(signUpRequest.getHobby())
                .intro(signUpRequest.getIntro())
                .socialType(signUpRequest.getSocialType())
                .role(Role.USER)
                .build();

        // 회원 관심사 추가
        this.addPrefTravelDestination(signUpRequest.getUserTravelDestinations(), userEntity);

        // 회원가입
        userRepository.save(userEntity);

    }

    /**
     * 회원의 선호 여행지 추가
     *
     * @param userTravelDestinations
     * @param userEntity
     */
    private void addPrefTravelDestination(Set<TravelDest> userTravelDestinations, UserEntity userEntity) {

        for (TravelDest travelDestination : userTravelDestinations) {
            TravelDestEntity travelDestEntity = travelDestRepository.findByTravelDestId(travelDestination)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.DATA_VALIDATION_ERROR));

            // 중간 테이블 객체 생성
            UserPrefTravelDestEntity userPrefTravelDestEntity = UserPrefTravelDestEntity.builder()
                    .userEntity(userEntity)
                    .travelDestEntity(travelDestEntity)
                    .build();

            // 각 객체로 불러올 수 있도록 리스트에 담아줌
            userEntity.addUserPrefTravelDest(userPrefTravelDestEntity);
            travelDestEntity.addUserPrefTravelDest(userPrefTravelDestEntity);
        }

    }
}
