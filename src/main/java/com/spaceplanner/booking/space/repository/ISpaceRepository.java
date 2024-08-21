package com.spaceplanner.booking.space.repository;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SmallSpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.typespace.entity.TypeSpace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISpaceRepository extends JpaRepository<Space, Long> {

    String FILTER_SPACE = "SELECT s FROM Space s LEFT JOIN TypeSpace ts ON s.typeSpace.id = ts.id WHERE s.floor=:floor AND s.available=:available AND ts.name LIKE %:typeSpace%";

    Boolean existsSpaceByName(String name);

    @Query("SELECT NEW com.spaceplanner.booking.space.entity.dto.SpaceDto(s.id, s.name, s.floor, s.description, s.capacity, s.available, ts.name) FROM Space s JOIN FETCH TypeSpace ts on s.typeSpace.id = ts.id")
    Page<SpaceDto> findAllSpaceDto(Pageable pageable);

    Integer countByTypeSpace(TypeSpace typeSpace);

    @Query("SELECT s.available FROM Space s WHERE s.id = :id")
    Boolean availableSpace(@Param("id") Long id);

    List<Space> findAllSpaceByFloor(Integer floor);

    @Query(FILTER_SPACE)
    List<Space> filterSpaceDto(@Param("floor") Integer floor, @Param("available") Boolean available, @Param("typeSpace") String typeSpace);

    @Query("SELECT NEW com.spaceplanner.booking.space.entity.dto.SmallSpaceDto( s.name, s.floor, s.available) FROM Space s")
    List<SmallSpaceDto> findAllSmallSpace();
}
