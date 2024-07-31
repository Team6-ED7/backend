package com.spaceplanner.booking.user.controller;

import com.spaceplanner.booking.user.entity.User;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        if (userDto == null) {
            throw new RuntimeException("UserDto is null");
        }
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }


    //TODO: Implement jwt token - Security - validation
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        if (userLoginDto == null) {
            throw new RuntimeException("UserLoginDto is null");
        }
        return new ResponseEntity<>(userService.loginUser(userLoginDto), HttpStatus.OK);
    }
}
