package com.spaceplanner.booking.space.entity.dto;

import com.spaceplanner.booking.typespace.entity.TypeSpace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceDto implements Serializable {

    private String name;
    private Integer floor;
    private String description;
    private Integer capacity;
    private Boolean available;
    private String typeSpace;
    private UUID codeUuid;
}
