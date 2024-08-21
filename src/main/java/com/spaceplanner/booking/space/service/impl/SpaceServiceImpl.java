package com.spaceplanner.booking.space.service.impl;

import com.spaceplanner.booking.Global.exceptionhandler.ModelAlreadyExistsException;
import com.spaceplanner.booking.Global.exceptionhandler.ModelNotFoundException;
import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.MassiveSpaceDto;
import com.spaceplanner.booking.space.entity.dto.SmallSpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceFilterCriteriaDto;
import com.spaceplanner.booking.space.repository.ISpaceRepository;
import com.spaceplanner.booking.space.service.ISpaceService;
import com.spaceplanner.booking.typespace.entity.TypeSpace;
import com.spaceplanner.booking.typespace.repository.ITypeSpaceRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SpaceServiceImpl implements ISpaceService {

    private final ISpaceRepository spaceRepository;
    private final ITypeSpaceRepository typeSpaceRepository;

    public SpaceServiceImpl(ISpaceRepository spaceRepository, ITypeSpaceRepository typeSpaceRepository) {
        this.spaceRepository = spaceRepository;
        this.typeSpaceRepository = typeSpaceRepository;
    }

    @Transactional()
    @Override
    public Space registerSpace(SpaceDto spaceDto) throws Exception {

        if (spaceRepository.existsSpaceByName(spaceDto.getName())) {
            throw new ModelAlreadyExistsException("Space already exists");
        }

        return spaceRepository.save(mapSpaceDtoToSpace(spaceDto));

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
            throw new ModelNotFoundException("The floor not found ");
        }

        return listSpace.stream().map(this::mapSpaceToSpaceDto).collect(Collectors.toList());
    }

    @Override
    public List<SpaceDto> filterSpaceDto(SpaceFilterCriteriaDto spaceFilter) throws Exception {

        List<Space> listSpace = spaceRepository.filterSpaceDto(spaceFilter.getFloor(), spaceFilter.getAvailable(), spaceFilter.getTypeSpace());

        return listSpace.stream().map(this::mapSpaceToSpaceDto).collect(Collectors.toList());

    }

    @Override
    public List<SmallSpaceDto> findAllSmallSpaceDto() {
        return spaceRepository.findAllSmallSpace();
    }

    private SpaceDto mapSpaceToSpaceDto(Space space) {
        return SpaceDto.builder()
                .id(space.getId())
                .name(space.getName())
                .floor(space.getFloor())
                .description(space.getDescription())
                .capacity(space.getCapacity())
                .available(space.getAvailable())
                .typeSpace(space.getTypeSpace().getName())
                .build();
    }

    private Space mapSpaceDtoToSpace(SpaceDto spaceDto) throws Exception {

        TypeSpace typeSpace = typeSpaceRepository.findTypeSpaceByNameIgnoreCase(spaceDto.getTypeSpace());

        if (typeSpace == null) {
            throw new ModelNotFoundException("Type Space not found");
        }

        return Space.builder()
                .id(spaceDto.getId())
                .name(spaceDto.getName())
                .floor(spaceDto.getFloor())
                .description(spaceDto.getDescription())
                .capacity(spaceDto.getCapacity())
                .available(spaceDto.getAvailable())
                .typeSpace(typeSpace)
                .build();
    }


}
