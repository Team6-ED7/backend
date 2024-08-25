package com.spaceplanner.booking.reservation.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SpaceFloorDateDto implements Serializable {

    private Integer floor;

    private LocalDate startDate;
}
