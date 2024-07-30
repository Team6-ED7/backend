package com.spaceplanner.booking.typespace.service;

import com.spaceplanner.booking.typespace.entity.TypeSpace;
import com.spaceplanner.booking.typespace.entity.dto.TypeSpaceDto;
import jakarta.validation.Valid;

public interface ITypeSpaceService {

    TypeSpace registerTypeSpace(@Valid TypeSpaceDto typeSpaceDto);

}
