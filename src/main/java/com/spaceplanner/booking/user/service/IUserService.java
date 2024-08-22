package com.spaceplanner.booking.user.service;

import com.spaceplanner.booking.user.entity.User;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {



    UserDetails loadUserByUsername(String email);



}