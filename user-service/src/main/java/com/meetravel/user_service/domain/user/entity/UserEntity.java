package com.meetravel.user_service.domain.user.entity;

import com.meetravel.module_common.audit.BaseEntity;
import com.meetravel.user_service.domain.user.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert // INSERT 구문 생성 시점에 null이 아닌 컬럼들만 포함하며,
public class UserEntity extends BaseEntity implements UserDetails {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PASSWORD")
    @Builder.Default
    private String password = ""; // Default 값으로 빈 문자열 설정

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "PROFILE_IMAGE")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRAVEL_COUNT")
    private TravelFrequency travelFrequency;

    @Enumerated(EnumType.STRING)
    @Column(name = "SCHEDULE_TYPE")
    private ScheduleType scheduleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "PLANNING_TYPE")
    private PlanningType planningType;

    @Column(name = "MBTI")
    private String mbti;

    @Column(name = "HOBBY")
    private String hobby;

    @Column(name = "INTRO")
    private String intro;

    @Enumerated(EnumType.STRING)
    @Column(name = "SOCIAL_TYPE")
    private SocialType socialType; // KAKAO

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<UserPrefTravelDestEntity> userPrefTravelDestEntities = new HashSet<>();


    // 회원의 선호여행지 추가(등록)
    public void addUserPrefTravelDest(UserPrefTravelDestEntity userPrefTravelDestEntity) {
        userPrefTravelDestEntities.add(userPrefTravelDestEntity);
    }


    /**
     * UserDetails implements 메소드
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
