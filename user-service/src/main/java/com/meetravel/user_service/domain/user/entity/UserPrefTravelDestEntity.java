package com.meetravel.user_service.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "user_pref_travel_dest")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert // INSERT 구문 생성 시점에 null이 아닌 컬럼들만 포함하며,
public class UserPrefTravelDestEntity {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Id
    @Column(name = "DEST_ID")
    private Integer destId;

}