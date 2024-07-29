package com.spaceplanner.booking.typespace.repository;

import com.spaceplanner.booking.typespace.entity.TypeSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeSpaceRepository extends JpaRepository<TypeSpace, Long> {

    Boolean existsTypeSpaceByName(String name);
    TypeSpace findTypeSpaceByName(String name);
}
