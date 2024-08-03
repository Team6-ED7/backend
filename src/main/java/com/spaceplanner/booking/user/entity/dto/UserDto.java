package com.spaceplanner.booking.user.entity.dto;

import com.spaceplanner.booking.user.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be a minimum of 2 and a maximum of 50 characters.")
    private String name;

    @NotBlank(message = "Lastname is required")
    @Size(min = 2, max = 100, message = "Lastname must be a minimum of 2 and a maximum of 50 characters.")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not in the correct format: example@mail.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be a minimum of 8 and a maximum of 100 characters.")
    private String password;

}
