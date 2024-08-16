package com.spaceplanner.booking.space.controller;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.MassiveSpaceDto;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.service.ISpaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    @Autowired
    private ISpaceService spaceService;

    @PostMapping("/register")
    public ResponseEntity<Space> registerSpace(@Valid @RequestBody SpaceDto spaceDto) throws Exception{
        return new ResponseEntity<>(spaceService.registerSpace(spaceDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PagedModel<SpaceDto>> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return new ResponseEntity<>(spaceService.getSpaces(pageable), HttpStatus.OK);
    }

    @PostMapping("/massive-register")
    public ResponseEntity<Void> registerMassiveSpace(@Valid @RequestBody MassiveSpaceDto massiveSpaceDto) throws Exception{
        spaceService.registerMassiveSpace(massiveSpaceDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
