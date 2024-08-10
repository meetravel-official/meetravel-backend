package com.meetravel.user_service.domain.user.repository;

import com.meetravel.user_service.domain.user.entity.UserPrefTravelDestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrefTravelDestRepository extends JpaRepository<UserPrefTravelDestEntity, Long>, UserPrefTravelDestRepositoryCustom {


}
