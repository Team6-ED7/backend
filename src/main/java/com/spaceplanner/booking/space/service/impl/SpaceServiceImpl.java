package com.spaceplanner.booking.space.service.impl;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.repository.ISpaceRepository;
import com.spaceplanner.booking.space.service.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements ISpaceService {
    @Autowired
    private ISpaceRepository spaceRepository;


    @Override
    public Space registerSpace(SpaceDto spaceDto) {
    //TODO:create method to check if space already exists. generateUUID for space
        Space space = new Space();
        space.setName(spaceDto.getName());
        space.setFloor(spaceDto.getFloor());
        space.setDescription(spaceDto.getDescription());
        space.setAvailable(spaceDto.isAvailable());
        return spaceRepository.save(space);



    }
}
