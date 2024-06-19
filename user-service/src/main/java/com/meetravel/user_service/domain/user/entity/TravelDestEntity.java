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
@Table(name = "TRAVEL_DEST")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert // INSERT 구문 생성 시점에 null이 아닌 컬럼들만 포함하며,
// 컬럼의 지정된 default 값을 적용시키며 INSERT할 때 사용할 수 있다.
public class TravelDestEntity {

    @Id
    @Column(name = "DEST_ID")
    private String destId;

    @Column(name = "DEST_NAME")
    private String destName;

}
