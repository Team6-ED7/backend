package com.spaceplanner.booking.reservation.controller;


import com.spaceplanner.booking.reservation.entity.ReservationEntity;
import com.spaceplanner.booking.reservation.entity.dto.ReservationDto;
import com.spaceplanner.booking.reservation.entity.dto.SpaceAvailableDto;
import com.spaceplanner.booking.reservation.entity.dto.SpaceFloorDateDto;
import com.spaceplanner.booking.reservation.service.ReservationService;

import com.spaceplanner.booking.user.entity.User;
import com.spaceplanner.booking.user.repository.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")

public class ReservationController {


    private final ReservationService reservationService;
    private final IUserRepository userRepository;

    public ReservationController (ReservationService reservationService, IUserRepository userRepository) {
        this.reservationService = reservationService;
        this.userRepository = userRepository;
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

    @GetMapping("/user/")
    /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<List<String>> getReservationsByUser(@Valid @RequestBody String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<String> reservedSpaceNames = reservationService.findByUserEmail (userEmail);
        return new ResponseEntity<>(reservedSpaceNames, HttpStatus.OK);
    }

    @GetMapping("/space/{spaceId}")
    /* @IsUser ({"USER"})*/ //TODO: IMPLEMENT NEXT RELEASE AFTER MVP IS DONE
    public ResponseEntity<List<ReservationEntity>> getReservationsBySpace(@PathVariable Long spaceId) {

        List<ReservationEntity> reservations = reservationService.findBySpaceId(spaceId);

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/floor-date")
    public ResponseEntity<List<Object>> getAvailableSpaceDto(@Valid @RequestBody SpaceFloorDateDto space){
        List<Object> spaceAvailableDto = reservationService.findAvailableSpaceDtoByFloorAndDate(space);
        return new ResponseEntity<>(spaceAvailableDto, HttpStatus.OK);
    }
}
