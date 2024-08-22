package com.spaceplanner.booking.reservation.controller;


import com.spaceplanner.booking.reservation.entity.ReservationEntity;
import com.spaceplanner.booking.reservation.entity.dto.ReservationDto;
import com.spaceplanner.booking.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")

public class ReservationController {


    private final ReservationService reservationService;

    public ReservationController (ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
   /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<ReservationEntity> createReservation(@RequestBody ReservationDto reservationDto) {

        ReservationEntity newReservation = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(newReservation, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<ReservationEntity> getReservationById(@PathVariable Long id) {
        Optional<ReservationEntity> reservation = reservationService.findById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<List<ReservationEntity>> getReservationsByUser(@PathVariable Long userId) {
        List<ReservationEntity> reservations = reservationService.findByUserId(userId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/space/{spaceId}")
    /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<List<ReservationEntity>> getReservationsBySpace(@PathVariable Long spaceId) {
        List<ReservationEntity> reservations = reservationService.findBySpaceId(spaceId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
