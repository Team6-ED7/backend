package com.spaceplanner.booking.space.entity.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SmallSpaceDto implements Serializable {

    private String name;

    private Integer floor;

    private Boolean available;
}
