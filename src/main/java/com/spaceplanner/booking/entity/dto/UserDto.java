package com.spaceplanner.booking.entity.dto;

import com.spaceplanner.booking.entity.Rol;
import lombok.Data;


@Data
public class UserDto {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Rol rol;

}
