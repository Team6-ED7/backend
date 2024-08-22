package com.spaceplanner.booking.typespace.service;

import com.spaceplanner.booking.typespace.entity.TypeSpace;
import com.spaceplanner.booking.typespace.entity.dto.TypeSpaceDto;

public interface ITypeSpaceService {

    TypeSpace registerTypeSpace(TypeSpaceDto typeSpaceDto);

}
