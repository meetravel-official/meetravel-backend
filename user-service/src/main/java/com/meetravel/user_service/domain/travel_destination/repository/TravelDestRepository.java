package com.meetravel.user_service.domain.travel_destination.repository;

import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.user_service.domain.travel_destination.entity.TravelDestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelDestRepository extends JpaRepository<TravelDestEntity, TravelDest> {
    Optional<TravelDestEntity> findByTravelDestId(Long travelDestId);
}
