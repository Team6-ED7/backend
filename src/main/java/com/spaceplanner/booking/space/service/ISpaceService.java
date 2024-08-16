package com.spaceplanner.booking.space.service;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface ISpaceService {

  /*  Space registerSpace(@Valid SpaceDto spaceDto) throws Exception;*/

    PagedModel<SpaceDto> getSpaces(Pageable pageable);

}
