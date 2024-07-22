package com.spaceplanner.booking.user.service.impl;

import com.spaceplanner.booking.user.entity.User;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.repository.IUserRepository;
import com.spaceplanner.booking.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRol(userDto.getRol());
        return userRepository.save(user);

    }



}
