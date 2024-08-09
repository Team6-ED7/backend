package com.spaceplanner.booking.user.service.impl;

import com.spaceplanner.booking.user.entity.RoleEntity;
import com.spaceplanner.booking.user.entity.RoleEnum;
import com.spaceplanner.booking.user.entity.UserEntity;
import com.spaceplanner.booking.user.entity.dto.UserDto;
import com.spaceplanner.booking.user.entity.dto.UserLoginDto;
import com.spaceplanner.booking.user.repository.IRoleRepository;
import com.spaceplanner.booking.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El email " + email + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        return new User(userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isAccountNoLocked(),
                userEntity.isEnabled(),
                authorityList);
    }

    public UserEntity registerUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword())); // Cifrar la contraseña
        userEntity.setName(userDto.getName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEnabled(true); // Activar el usuario
        userEntity.setAccountNoExpired(true);
        userEntity.setAccountNoLocked(true);


        RoleEntity userRole = roleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

        Set<RoleEntity> roles = userEntity.getRoles();
        roles.add(userRole);
        userEntity.setRoles(roles);





        return userRepository.save(userEntity);
    }

    public UserEntity loginUser(UserLoginDto userLoginDto) {
        UserEntity userEntity = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("El email " + userLoginDto.getEmail() + " no existe."));
        if (userEntity.getPassword().equals(userLoginDto.getPassword())) {
            return userEntity;
        } else {
            throw new UsernameNotFoundException("Contraseña incorrecta");
        }
    }
}
