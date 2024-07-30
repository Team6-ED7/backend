package com.spaceplanner.booking.space.service.impl;

import com.spaceplanner.booking.Global.exception.BusinessException;
import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.repository.ISpaceRepository;
import com.spaceplanner.booking.space.service.ISpaceService;
import com.spaceplanner.booking.typespace.entity.TypeSpace;
import com.spaceplanner.booking.typespace.repository.ITypeSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpaceServiceImpl implements ISpaceService {

    @Autowired
    private ISpaceRepository spaceRepository;

    @Autowired
    private ITypeSpaceRepository typeSpaceRepository;
//    private ITypeSpaceService typeSpaceService;

    @Transactional()
    @Override
    public Space registerSpace(SpaceDto spaceDto) {

        if(spaceRepository.existsSpaceByCodeUuid(spaceDto.getCodeUuid())) {
            throw new BusinessException("409", HttpStatus.CONFLICT, "Space already exists");
        }

        Space space = new Space();
        space.setName(spaceDto.getName());
        space.setFloor(spaceDto.getFloor());
        space.setDescription(spaceDto.getDescription());
        space.setAvailable(spaceDto.getAvailable());
        space.setCapacity(spaceDto.getCapacity());

        TypeSpace typeSpace = typeSpaceRepository.findTypeSpaceByName(spaceDto.getTypeSpace());
        space.setTypeSpace(typeSpace);

//        space.setCodeUuid(UUID.randomUUID());
        return spaceRepository.save(space);

    }

    @Override
    public PagedModel<SpaceDto> getSpaces(Pageable pageable) {
        return new PagedModel<>(spaceRepository.findAllSpaceDto(pageable));
    }


}
