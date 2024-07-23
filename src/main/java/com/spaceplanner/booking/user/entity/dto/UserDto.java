package com.spaceplanner.booking.user.entity.dto;

import com.spaceplanner.booking.user.entity.Rol;
import lombok.Data;


@Data
public class UserDto {
    private String name;
    private String lastName;
    private String email;
    private String password;


}
