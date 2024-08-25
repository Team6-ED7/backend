package com.spaceplanner.booking.space.controller;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.MassiveSpaceDto;
import com.spaceplanner.booking.space.entity.dto.SmallSpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceFilterCriteriaDto;
import com.spaceplanner.booking.space.service.ISpaceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    final private ISpaceService spaceService;

    public SpaceController(ISpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping("/register")
    /* @IsUser ({"ADMIN"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<Space> registerSpace(@Valid @RequestBody SpaceDto spaceDto) throws Exception {
        return new ResponseEntity<>(spaceService.registerSpace(spaceDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PagedModel<SpaceDto>> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return new ResponseEntity<>(spaceService.getSpaces(pageable), HttpStatus.OK);
    }

    @PostMapping("/massive-register")
    /* @IsUser ({"ADMIN"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<Void> registerMassiveSpace(@Valid @RequestBody MassiveSpaceDto massiveSpaceDto) throws Exception {
        spaceService.registerMassiveSpace(massiveSpaceDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/available/{id}")
    /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<Boolean> isAvailableSpace(@PathVariable("id") Long spaceId) throws Exception {
        return new ResponseEntity<>(spaceService.isAvailableSpace(spaceId), HttpStatus.OK);
    }

    @GetMapping("/floor/{floor}")
    /* @IsUser ({"ADMIN"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<List<SpaceDto>> findAllSpacesByFloor(@PathVariable("floor") Integer floor) throws Exception {
        return new ResponseEntity<>(spaceService.findAllSpaceDtoByFloor(floor), HttpStatus.OK);
    }

    @PostMapping("/filter")
    /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<List<SpaceDto>> filterSpace(@Valid @RequestBody SpaceFilterCriteriaDto spaceFilter) throws Exception {
        return new ResponseEntity<>(spaceService.filterSpaceDto(spaceFilter), HttpStatus.OK);
    }

    @GetMapping("/small-spaces")
    /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<List<SmallSpaceDto>> findAllSmallSpace() {
        return new ResponseEntity<>(spaceService.findAllSmallSpaceDto(), HttpStatus.OK);
    }


}
