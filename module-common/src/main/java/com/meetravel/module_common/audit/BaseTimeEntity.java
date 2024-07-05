package com.meetravel.module_common.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //  상위 클래스인 BaseTimeEntity의 필드들을 하위 엔티티 클래스에 포함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false, name = "CREATE_DTM")
    private LocalDateTime createDateTime;   // 생성일시

    @LastModifiedDate
    @Column(nullable = false, name = "UPDATE_DTM")
    private LocalDateTime updateDateTime;   // 수정일시

}
