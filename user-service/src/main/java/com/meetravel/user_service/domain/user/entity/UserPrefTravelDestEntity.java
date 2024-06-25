package com.meetravel.user_service.domain.user.entity;

import com.meetravel.user_service.domain.travel_destination.entity.TravelDestEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "user_pref_travel_dest", uniqueConstraints = { // 데이터 중복 방지를 위해 유니크 제약조건 설정
        @UniqueConstraint(columnNames = {"USER_ID", "TRAVEL_DEST_ID"})
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrefTravelDestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "TRAVEL_DEST_ID", nullable = false)
    private TravelDestEntity travelDestEntity;

}
