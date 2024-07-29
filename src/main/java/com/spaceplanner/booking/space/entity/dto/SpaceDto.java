package com.spaceplanner.booking.space.entity.dto;

import com.spaceplanner.booking.typespace.entity.TypeSpace;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class SpaceDto implements Serializable {

    private String name;
    private Integer floor;
    private String description;
    private Integer capacity;
    private Boolean available;
//    private TypeSpace typeSpace;
    private String typeSpace;
    private UUID codeUuid;
}
