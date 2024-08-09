package com.spaceplanner.booking.user.controller;

import com.spaceplanner.booking.user.entity.UserEntity;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.service.impl.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDetailServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@Valid @RequestBody UserDto userDto) throws Exception {
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    //TODO: Implement jwt token - Security - validation
    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) throws Exception{
        return new ResponseEntity<>(userService.loginUser(userLoginDto), HttpStatus.OK);
    }
}