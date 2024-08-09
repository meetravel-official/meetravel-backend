package com.meetravel.user_service.domain.user.repository;

import com.meetravel.module_common.enums.TravelDest;

import java.util.List;

public interface UserPrefTravelDestRepositoryCustom {

    List<TravelDest> getUserPrefTravelDestList(String userId);
}
