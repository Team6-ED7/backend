package com.spaceplanner.booking.space.service;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.MassiveSpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceFilterCriteriaDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface ISpaceService {

    Space registerSpace(@Valid SpaceDto spaceDto) throws Exception;

    PagedModel<SpaceDto> getSpaces(Pageable pageable);

    void registerMassiveSpace(MassiveSpaceDto massiveSpaceDto) throws Exception;

    Boolean isAvailableSpace(Long id) throws Exception;

    List<SpaceDto> findAllSpaceDtoByFloor(Integer floor) throws Exception;

    List<SpaceDto> filterSpaceDto(SpaceFilterCriteriaDto spaceFilterCriteriaDto) throws Exception;

}
