package com.meetravel.user_service.domain.travel_destination.entity;

import com.meetravel.module_common.audit.BaseEntity;
import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.user_service.domain.user.entity.UserPrefTravelDestEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "travel_dest")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelDestEntity extends BaseEntity {

    @Id
    @Column(name = "TRAVEL_DEST_ID")
    @Enumerated(EnumType.STRING)
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
