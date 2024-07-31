package com.spaceplanner.booking.space.controller;

import com.spaceplanner.booking.Global.exception.RequestException;
import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.service.ISpaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spaces")
@CrossOrigin(origins = "*")
public class SpaceController {

    @Autowired
    private ISpaceService spaceService;

    @PostMapping("/register")
    public ResponseEntity<Space> registerSpace(@Valid @RequestBody SpaceDto spaceDto) {
        if (spaceDto == null) {
            throw new RequestException("401", "SpaceDto is null");
        }

        return new ResponseEntity<>(spaceService.registerSpace(spaceDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PagedModel<SpaceDto>> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return new ResponseEntity<>(spaceService.getSpaces(pageable), HttpStatus.OK);
    }
}
