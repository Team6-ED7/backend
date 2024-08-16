package com.spaceplanner.booking.reservation.controller;

import com.spaceplanner.booking.reservation.entity.ReservationEntity;
import com.spaceplanner.booking.reservation.entity.dto.ReservationDto;
import com.spaceplanner.booking.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")

public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationEntity> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        try {
            ReservationEntity reservation = reservationService.createReservation(reservationDto);
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationEntity> getReservationById(@PathVariable Long id) {
        Optional<ReservationEntity> reservation = reservationService.findById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationEntity>> getReservationsByUser(@PathVariable Long userId) {
        List<ReservationEntity> reservations = reservationService.findByUserId(userId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/space/{spaceId}")
    public ResponseEntity<List<ReservationEntity>> getReservationsBySpace(@PathVariable Long spaceId) {
        List<ReservationEntity> reservations = reservationService.findBySpaceId(spaceId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
