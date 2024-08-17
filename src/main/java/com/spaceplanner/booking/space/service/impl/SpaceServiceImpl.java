package com.spaceplanner.booking.space.service.impl;

import com.spaceplanner.booking.Global.exceptionhandler.ModelAlreadyExistsException;
import com.spaceplanner.booking.Global.exceptionhandler.ModelNotFoundException;
import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.MassiveSpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.repository.ISpaceRepository;
import com.spaceplanner.booking.space.service.ISpaceService;
import com.spaceplanner.booking.typespace.entity.TypeSpace;
import com.spaceplanner.booking.typespace.repository.ITypeSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class SpaceServiceImpl implements ISpaceService {

    @Autowired
    private ISpaceRepository spaceRepository;

    @Autowired
    private ITypeSpaceRepository typeSpaceRepository;

    @Transactional()
    @Override
    public Space registerSpace(SpaceDto spaceDto) throws Exception {

        if (spaceRepository.existsSpaceByCodeUuid(spaceDto.getCodeUuid())) {
            throw new ModelAlreadyExistsException("Space already exists");
        }

        Space space = Space.builder()
                .name(spaceDto.getName())
                .floor(spaceDto.getFloor())
                .description(spaceDto.getDescription())
                .available(spaceDto.getAvailable())
                .capacity(spaceDto.getCapacity())
                .build();

        TypeSpace typeSpace = typeSpaceRepository.findTypeSpaceByNameIgnoreCase(spaceDto.getTypeSpace());

        if (typeSpace == null) {
            throw new ModelNotFoundException("Type Space not found");
        }

        space.setTypeSpace(typeSpace);

        return spaceRepository.save(space);

    }

    @Override
    public PagedModel<SpaceDto> getSpaces(Pageable pageable) {
        return new PagedModel<>(spaceRepository.findAllSpaceDto(pageable));
    }


    @Transactional()
    @Override
    public void registerMassiveSpace(MassiveSpaceDto massiveSpaceDto) throws Exception {

        TypeSpace typeSpace = typeSpaceRepository.findTypeSpaceByNameIgnoreCase(massiveSpaceDto.getTypeSpace());
        if (typeSpace == null) {
            throw new ModelNotFoundException("Type Space not found");
        }

        int existingSpaces = spaceRepository.countByTypeSpace(typeSpace);

        List<Space> spacesList = new ArrayList<>();

        IntStream.range(0, massiveSpaceDto.getTotalSpace()).forEach(count -> {
            Space space = createNewSpace(count + existingSpaces + 1, massiveSpaceDto.getFloor(), typeSpace);
            spacesList.add(space);
        });

        spaceRepository.saveAll(spacesList);
    }

    private Space createNewSpace(int count, Integer floor, TypeSpace typeSpace) {

        return Space.builder()
                .name(setAbbreviatedName(typeSpace.getName(), count))
                .floor(floor)
                .typeSpace(typeSpace)
                .description("A single space to work")
                .capacity(1)
                .available(true)
                .build();
    }

    private String setAbbreviatedName(String fullName, int count) {

        StringBuilder abbreviatedName = new StringBuilder();

        List<String> words = Arrays.asList(fullName.split(" "));

        if (words.size() > 1) {
            words.forEach(word -> abbreviatedName.append(word.charAt(0)));
        } else {
            words.forEach(word -> abbreviatedName.append(word, 0, Math.min(word.length(), 3)));
        }

        abbreviatedName.append("-");
        abbreviatedName.append(count);

        return abbreviatedName.toString().toUpperCase();
    }

    @Override
    public Boolean isAvailableSpace(Long id) throws Exception {
        Boolean isAvailable = spaceRepository.availableSpace(id);

        if (isAvailable == null) {
            throw new ModelNotFoundException("Space not found");
        }

        return isAvailable;
    }

    @Override
    public List<SpaceDto> findAllSpaceDtoByFloor(Integer floor) throws Exception {

        List<Space> listSpace = spaceRepository.findAllSpaceByFloor(floor);

        if (listSpace.isEmpty()) {
            throw new ModelNotFoundException("Floor not found ");
        }

        List<SpaceDto> spaceDtoList = new ArrayList<>();

        listSpace.forEach(space -> {
            SpaceDto spaceDto = SpaceDto.builder()
                    .id(space.getId())
                    .name(space.getName())
                    .floor(space.getFloor())
                    .description(space.getDescription())
                    .capacity(space.getCapacity())
                    .available(space.getAvailable())
                    .typeSpace(space.getTypeSpace().getName())
                    .codeUuid(space.getCodeUuid())
                    .build();

            spaceDtoList.add(spaceDto);
        });

        return spaceDtoList;
    }


}
