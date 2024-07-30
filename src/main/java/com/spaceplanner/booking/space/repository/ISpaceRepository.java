package com.spaceplanner.booking.space.repository;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISpaceRepository extends JpaRepository<Space, Long> {

    Boolean existsSpaceByCodeUuid(UUID uuid);

    @Query("SELECT NEW com.spaceplanner.booking.space.entity.dto.SpaceDto(s.name, s.floor, s.description, s.capacity, s.available, ts.name, s.codeUuid) FROM Space s JOIN FETCH TypeSpace ts on s.typeSpace.id = ts.id")
    Page<SpaceDto> findAllSpaceDto(Pageable pageable);
}
