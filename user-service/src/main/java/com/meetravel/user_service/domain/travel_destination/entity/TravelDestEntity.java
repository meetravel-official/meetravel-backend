package com.meetravel.user_service.domain.travel_destination.entity;

import com.meetravel.user_service.domain.user.entity.UserPrefTravelDestEntity;
import com.meetravel.user_service.domain.user.enums.TravelDest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TRAVEL_DEST")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert // INSERT 구문 생성 시점에 null이 아닌 컬럼들만 포함하며,
// 컬럼의 지정된 default 값을 적용시키며 INSERT할 때 사용할 수 있다.
public class TravelDestEntity {

    @Id
    @Column(name = "TRAVEL_DEST_ID")
    private TravelDest travelDestId;

    @Column(name = "TRAVEL_DEST_NAME")
    private String travelDestName;

    @OneToMany(mappedBy = "travelDestEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<UserPrefTravelDestEntity> userPrefTravelDestEntities = new HashSet<>();

    public void addUserPrefTravelDest(UserPrefTravelDestEntity userPrefTravelDestEntity) {
        userPrefTravelDestEntities.add(userPrefTravelDestEntity);
    }

}
