package com.meetravel.user_service.domain.travel_destination.entity;

import com.meetravel.module_common.audit.BaseEntity;
import com.meetravel.module_common.converter.TravelDestConverter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelDestId;

    @Column(name = "TRAVEL_DEST")
    @Convert(converter = TravelDestConverter.class)
    private TravelDest travelDest;

    @OneToMany(mappedBy = "travelDest", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<UserPrefTravelDestEntity> userPrefTravelDests = new HashSet<>();

    public void addUserPrefTravelDest(UserPrefTravelDestEntity userPrefTravelDest) {
        this.userPrefTravelDests.add(userPrefTravelDest);
    }

}
