package com.spaceplanner.booking.controller;

import com.spaceplanner.booking.entity.User;
import com.spaceplanner.booking.entity.dto.UserDto;
import com.spaceplanner.booking.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class userController {

    @Autowired
    private final UserServiceImpl userServiceImpl;

    public userController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userServiceImpl.registerUser(userDto), HttpStatus.CREATED);
    }
}
