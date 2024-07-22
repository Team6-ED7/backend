package com.spaceplanner.booking.service.impl;

import com.spaceplanner.booking.entity.User;
import com.spaceplanner.booking.entity.dto.UserDto;
import com.spaceplanner.booking.repository.IUserRepository;
import com.spaceplanner.booking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
