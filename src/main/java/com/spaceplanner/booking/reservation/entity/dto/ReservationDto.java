package com.spaceplanner.booking.reservation.entity.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {

    private String userEmail;
    private String spaceName;
    private LocalDate startDate;
    private Boolean status;

}



