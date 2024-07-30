package com.spaceplanner.booking.user.service.impl;

import com.spaceplanner.booking.Global.exception.BusinessException;
import com.spaceplanner.booking.user.entity.User;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.repository.IUserRepository;
import com.spaceplanner.booking.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new BusinessException("408", HttpStatus.CONFLICT, "User already exists");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);

    }

    @Override
    public User loginUser(UserLoginDto userLoginDto) {
        Optional<User> user = userRepository.findByEmail(userLoginDto.getEmail());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(userLoginDto.getPassword())) {
                return user.get();
            }
        }
        throw new BusinessException("401", HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }


}
