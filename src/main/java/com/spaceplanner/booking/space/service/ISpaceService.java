package com.spaceplanner.booking.space.service;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import jakarta.validation.Valid;

public interface ISpaceService {

    Space registerSpace(@Valid SpaceDto spaceDto);

}
