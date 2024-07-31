package com.spaceplanner.booking.typespace.controller;

import com.spaceplanner.booking.Global.exception.RequestException;
import com.spaceplanner.booking.typespace.entity.TypeSpace;
import com.spaceplanner.booking.typespace.entity.dto.TypeSpaceDto;
import com.spaceplanner.booking.typespace.service.ITypeSpaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/typespaces")
@CrossOrigin(origins = "*")
public class TypeSpaceController {

    @Autowired
    ITypeSpaceService typeSpaceService;

    @PostMapping("/register")
    public ResponseEntity<TypeSpace> registerTypeSpace(@Valid @RequestBody TypeSpaceDto typeSpaceDto) {
        if (typeSpaceDto == null){
            throw new RequestException("401", "TypeSpaceDto is null");
        }

        return new ResponseEntity<>(typeSpaceService.registerTypeSpace(typeSpaceDto), HttpStatus.CREATED);
    }
}
