package com.spaceplanner.booking.user.service.impl;

import com.spaceplanner.booking.Global.util.JwtUtils;
import com.spaceplanner.booking.user.entity.RoleEnum;
import com.spaceplanner.booking.user.entity.User;


import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginResponse;
import com.spaceplanner.booking.user.repository.IUserRepository;
import com.spaceplanner.booking.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService, IUserService {


    private final IUserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found."));
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        user.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name())));
        user.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));
        return new org.springframework.security.core.userdetails.User (user.getEmail(), passwordEncoder.encode (user.getPassword()) , user.isEnabled(),
                user.isAccountNoExpired(), user.isCredentialNoExpired(), user.isAccountNoLocked(), authorityList);
    }

    public void registerUser(UserDto userDto) {

        User user = User.builder()
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
               userRepository.save(user);
    }
//TODO implement the loginUser method NOT SEND TO FRONTEND. fIX THE BUG
    public UserLoginResponse loginUser(UserLoginDto userLoginDto) {

        User user = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new BadCredentialsException("User not found"));


        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }


        String jwtToken = jwtUtils.createToken(loadUserByUsername(userLoginDto.getEmail()));


        return new UserLoginResponse(jwtToken, user.getName());
    }

}

