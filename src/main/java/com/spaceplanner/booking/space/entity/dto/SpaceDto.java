package com.spaceplanner.booking.space.entity.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SpaceDto {

    private String name;
    private int floor;
    private String description;
    private boolean available;
    private UUID codeUuid;
}
