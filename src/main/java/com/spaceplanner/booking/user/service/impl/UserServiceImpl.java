package com.spaceplanner.booking.user.service.impl;

import com.spaceplanner.booking.Global.exception.RequestException;
import com.spaceplanner.booking.Global.exceptionhandler.ModelAlreadyExistsException;
import com.spaceplanner.booking.Global.exceptionhandler.ModelNotFoundException;
import com.spaceplanner.booking.user.entity.User;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.repository.IUserRepository;
import com.spaceplanner.booking.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User registerUser(UserDto userDto) throws Exception {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ModelAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);

    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) throws Exception{
        Optional<User> user = userRepository.findByEmail(userLoginDto.getEmail());

        if (user.isPresent()) {
            if (user.get().getPassword().equals(userLoginDto.getPassword())) {
//                return new RequestException("405", "User logged in");
                throw new ModelNotFoundException("User logged in");
            } else {
//                throw new ModelNotFoundException("401", HttpStatus.UNAUTHORIZED, "Invalid password");
                throw new ModelNotFoundException("Invalid password");
            }
        } else {
//            throw new BusinessException("404", HttpStatus.NOT_FOUND, "User not found");
            throw new ModelNotFoundException("User not found");
        }
    }


}
