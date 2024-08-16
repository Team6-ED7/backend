package com.spaceplanner.booking.reservation.entity.dto;

import lombok.Data;

@Data
public class ReservationDto {

    private Long userId;
    private Long spaceId;
    private String startTime;
    private String endTime;
    private String status;
}
