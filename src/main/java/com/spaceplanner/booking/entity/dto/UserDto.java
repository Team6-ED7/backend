package com.spaceplanner.booking.entity.dto;

import com.spaceplanner.booking.entity.Rol;
import lombok.Data;


@Data
public class UserDto {
    private String name;
    private String LastName;
    private String Email;
    private String Password;
    private Rol rol;

}
