package com.meetravel.user_service.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserPrefTravelDestEntityId implements Serializable {

    @EqualsAndHashCode.Include
    private String userId;

    @EqualsAndHashCode.Include
    private Integer destId;

}