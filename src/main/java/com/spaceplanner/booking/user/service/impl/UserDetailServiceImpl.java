package com.spaceplanner.booking.user.service.impl;

import com.spaceplanner.booking.user.entity.RoleEntity;
import com.spaceplanner.booking.user.entity.RoleEnum;
import com.spaceplanner.booking.user.entity.UserEntity;


import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private IUserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found."));
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        userEntity.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name())));
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(userEntity.getEmail(), passwordEncoder.encode(userEntity.getPassword()), userEntity.isEnabled(),
                userEntity.isAccountNoExpired(), userEntity.isCredentialNoExpired(), userEntity.isAccountNoLocked(), authorityList);
    }

    public UserEntity registerUser(UserDto userDto) {

        UserEntity userEntity = UserEntity.builder()
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .roleEnum(RoleEnum.USER)
                .build();
        return userRepository.save(userEntity);
    }

    public UserEntity loginUser(UserLoginDto userLoginDto) {
        UserEntity userEntity = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User " + userLoginDto.getEmail() + " not found."));
        if (!passwordEncoder.matches(userLoginDto.getPassword(), userEntity.getPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }
        return userEntity;
    }
}
