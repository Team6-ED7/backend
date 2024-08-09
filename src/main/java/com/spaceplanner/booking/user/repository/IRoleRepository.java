package com.spaceplanner.booking.user.repository;

import com.spaceplanner.booking.user.entity.RoleEntity;
import com.spaceplanner.booking.user.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Boolean existsByRole(String role);
    Optional<RoleEntity> findByRole(String role);

    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);

}
