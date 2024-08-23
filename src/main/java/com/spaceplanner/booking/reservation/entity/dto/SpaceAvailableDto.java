package com.spaceplanner.booking.reservation.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpaceAvailableDto implements Serializable {

    private Long spaceId;

    private String name;

    private Integer floor;

    private Boolean available;
}
