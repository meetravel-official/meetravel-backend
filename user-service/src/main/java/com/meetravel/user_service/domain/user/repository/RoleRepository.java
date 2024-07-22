package com.meetravel.user_service.domain.user.repository;

import com.meetravel.user_service.domain.user.entity.RoleEntity;
import com.meetravel.user_service.domain.user.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRole(Role role);
}
