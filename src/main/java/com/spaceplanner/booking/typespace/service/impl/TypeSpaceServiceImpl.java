package com.spaceplanner.booking.typespace.service.impl;

import com.spaceplanner.booking.Global.exception.BusinessException;
import com.spaceplanner.booking.typespace.entity.TypeSpace;
import com.spaceplanner.booking.typespace.entity.dto.TypeSpaceDto;
import com.spaceplanner.booking.typespace.repository.ITypeSpaceRepository;
import com.spaceplanner.booking.typespace.service.ITypeSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TypeSpaceServiceImpl implements ITypeSpaceService {

    @Autowired
    ITypeSpaceRepository typeSpaceRepository;

    @Override
    public TypeSpace registerTypeSpace(TypeSpaceDto typeSpaceDto) {

        if(typeSpaceRepository.existsTypeSpaceByName(typeSpaceDto.getName())){
            throw new BusinessException("408", HttpStatus.CONFLICT, "Description already exists");
        }

        TypeSpace typeSpace = new TypeSpace();
        typeSpace.setName(typeSpaceDto.getName());
        return typeSpaceRepository.save(typeSpace);
    }
}
