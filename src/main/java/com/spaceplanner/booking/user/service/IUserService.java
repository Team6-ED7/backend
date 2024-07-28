package com.spaceplanner.booking.user.service;

import com.spaceplanner.booking.user.entity.User;

import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import jakarta.validation.Valid;

public interface  IUserService {

    User registerUser(@Valid UserDto userDto);
    User loginUser(@Valid UserLoginDto userLoginDto);

}
