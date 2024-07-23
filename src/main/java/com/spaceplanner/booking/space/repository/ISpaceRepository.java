package com.spaceplanner.booking.space.repository;

import com.spaceplanner.booking.space.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpaceRepository extends JpaRepository<Space, Long> {
}
