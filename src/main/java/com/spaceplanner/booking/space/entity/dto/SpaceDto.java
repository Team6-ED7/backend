package com.spaceplanner.booking.space.entity.dto;

import com.spaceplanner.booking.user.entity.Rol;
import lombok.Data;

@Data
public class SpaceDto {
    private String name;
    private int floor;
    private String description;
    private boolean available;

}
