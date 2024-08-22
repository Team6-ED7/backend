package com.spaceplanner.booking.user.controller;

import com.spaceplanner.booking.user.entity.User;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginResponse;
import com.spaceplanner.booking.user.service.impl.UserDetailServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private final UserDetailServiceImpl userService;


    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    /* @IsUser ({"PERMIT ALL"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        // Encrypt the password before saving the user
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.registerUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    /* @IsUser ({"PERMIT ALL"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        return new ResponseEntity<>(userService.loginUser(userLoginDto), HttpStatus.OK);
    }



}
