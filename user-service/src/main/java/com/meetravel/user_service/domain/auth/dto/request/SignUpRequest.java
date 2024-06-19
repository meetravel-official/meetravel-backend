package com.meetravel.user_service.domain.auth.dto.request;

import com.meetravel.user_service.domain.user.enums.SocialType;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String userId;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String profileImage;
    private Integer travelCount;
    private String travelStyle1;
    private String travelStyle2;
    private String mbti;
    private String hobby;
    private String intro;
    private SocialType socialType;

}
