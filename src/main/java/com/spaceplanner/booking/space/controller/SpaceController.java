package com.spaceplanner.booking.space.controller;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.entity.dto.SpaceDto;
import com.spaceplanner.booking.space.service.ISpaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    @Autowired
    private ISpaceService spaceService;

    @PostMapping("/register")
    public ResponseEntity<Space> registerSpace(@Valid @RequestBody SpaceDto spaceDto) {
        return new ResponseEntity<>(spaceService.registerSpace(spaceDto), HttpStatus.CREATED);
    }
}
