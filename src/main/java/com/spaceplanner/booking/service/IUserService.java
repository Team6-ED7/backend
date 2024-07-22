package com.spaceplanner.booking.service;

import com.spaceplanner.booking.entity.User;
import com.spaceplanner.booking.entity.dto.UserDto;
import jakarta.validation.Valid;

import java.util.List;

public interface  IUserService {

    User registerUser(@Valid UserDto userDto);

}
