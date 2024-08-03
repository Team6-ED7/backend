package com.spaceplanner.booking.user.controller;

import com.spaceplanner.booking.Global.exceptionhandler.ModelAlreadyExistsException;
import com.spaceplanner.booking.Global.exceptionhandler.ModelNotFoundException;
import com.spaceplanner.booking.user.entity.User;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.service.IUserService;
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
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) throws Exception {
//        if (userDto == null) {
//            throw new RuntimeException("UserDto is null");
//        }
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }


    //TODO: Implement jwt token - Security - validation
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) throws Exception{
//        if (userLoginDto == null) {
//            throw new RuntimeException("UserLoginDto is null");
//        }
        return new ResponseEntity<>(userService.loginUser(userLoginDto), HttpStatus.OK);
    }
}
