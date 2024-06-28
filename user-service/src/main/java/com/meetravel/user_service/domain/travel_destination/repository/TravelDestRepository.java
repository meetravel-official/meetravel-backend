package com.meetravel.user_service.domain.travel_destination.repository;

import com.meetravel.user_service.domain.travel_destination.entity.TravelDestEntity;
import com.meetravel.user_service.domain.user.enums.TravelDest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelDestRepository extends JpaRepository<TravelDestEntity, TravelDest> {

    Optional<TravelDestEntity> findByTravelDestId(TravelDest travelDest);
}
