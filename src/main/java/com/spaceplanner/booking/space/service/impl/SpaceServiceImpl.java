package com.spaceplanner.booking.space.service.impl;

import com.spaceplanner.booking.Global.exception.BusinessException;
import com.spaceplanner.booking.Global.exceptionhandler.ModelAlreadyExistsException;
import com.spaceplanner.booking.Global.exceptionhandler.ModelNotFoundException;
import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.entity.dto.TypeSpaceEnum;
import com.spaceplanner.booking.space.repository.ISpaceRepository;
import com.spaceplanner.booking.space.service.ISpaceService;
import com.spaceplanner.booking.typespace.entity.TypeSpace;
import com.spaceplanner.booking.typespace.repository.ITypeSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional()
 /*   @Override*/


    public Space registerSpace(String typeSpace, int floor, int qty ) throws Exception {
/*
        if(spaceRepository.existsSpaceByCodeUuid(spaceDto.getCodeUuid())) {
            throw new ModelAlreadyExistsException("Space already exists");
        }*/

       Space space = new Space();
       space.setName(typeSpace.split("\\(")[0].trim()

               .concat(String.valueOf(floor))

               .concat(String.valueOf(qty)));
       space.setFloor(floor);

       space.setDescription(extractDescription(typeSpace));
         space.setCapacity(1);
            space.setAvailable(true);
            space.setTypeSpace(TypeSpaceEnum.valueOf(typeSpace.split("\\(")[0].trim()));




 /*        space.setName(spaceDto.getName());
        space.setFloor(spaceDto.getFloor());
        space.setDescription(spaceDto.getDescription());
        space.setAvailable(spaceDto.getAvailable());
        space.setCapacity(spaceDto.getCapacity());
        space.setTypeSpace(spaceDto.getTypeSpace());*/

     /*   TypeSpace typeSpace = typeSpaceRepository.findTypeSpaceByName(spaceDto.getTypeSpace());

        if(typeSpace == null) {
            throw new ModelNotFoundException("Type Space not found");
        }

        space.setTypeSpace(typeSpace);*/

        return spaceRepository.save(space);

    }

    private String extractDescription(String typeSpace) {

        if (typeSpace.contains("(") && typeSpace.contains(")")) {
            return typeSpace.split("\\(")[1].replace(")", "");
        } else {
            return "";
        }
    }



    @Override
    public PagedModel<SpaceDto> getSpaces(Pageable pageable) {
        //   return new PagedModel<>(spaceRepository.findAllSpaceDto(pageable));
      return null;
    }
    }


