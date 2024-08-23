package com.spaceplanner.booking.reservation.service;

import com.spaceplanner.booking.reservation.entity.ReservationEntity;
import com.spaceplanner.booking.reservation.entity.ReservationStatus;
import com.spaceplanner.booking.reservation.entity.dto.ReservationDto;
import com.spaceplanner.booking.reservation.entity.dto.SpaceAvailableDto;
import com.spaceplanner.booking.reservation.entity.dto.SpaceFloorDateDto;
import com.spaceplanner.booking.reservation.repository.IReservationRepository;
import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.repository.ISpaceRepository;
import com.spaceplanner.booking.user.repository.IUserRepository;
import jakarta.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;


@Service

public class ReservationService {

    private final IReservationRepository reservationRepository;

    private final ISpaceRepository spaceRepository;

    private final IUserRepository userRepository;

    public ReservationService(IReservationRepository reservationRepository, ISpaceRepository spaceRepository, IUserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public ReservationEntity createReservation(ReservationDto reservationDto) {

//        validateReservationData(reservationDto);
        Space space = spaceRepository.findSpaceByNameCreation (reservationDto.getSpaceName());

        if (!isSpaceAvailable(space,reservationDto.getStartDate())  ) {
            throw new RuntimeException("Space is not available for the requested time.");
        }

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setUser(userRepository.findByEmail (reservationDto.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found")));
        reservationEntity.setSpace(space);
        reservationEntity.setStartDate (reservationDto.getStartDate ());
        reservationEntity.setStatus(ReservationStatus.APPROVED);
        space.setAvailable (false);
        return reservationRepository.save(reservationEntity);
    }

    @Transactional
    public boolean isSpaceAvailable(Space space, LocalDate startTime) {

        return reservationRepository.findAllBySpaceAndStartDate(space, startTime).isEmpty();
    }

    private void validateReservationData(ReservationDto reservationDto) {

        LocalDate startTime = reservationDto.getStartDate();


        if (startTime.isAfter(now())) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }


    }
    @Transactional
    public Optional<ReservationEntity> findById(Long id) {

        return reservationRepository.findById(id);
    }
    @Transactional
    public List<String> findByUserEmail(String userEmail) {


        return reservationRepository.findAllBy_Email (userEmail);
    }

    public List<ReservationEntity> findBySpaceId(Long spaceId) {
        return reservationRepository.findAllBySpace_Id(spaceId);
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void deleteReservation() {
      spaceRepository.findAll().forEach (space -> {
            reservationRepository.findAllBySpaceAndStartDate (space, LocalDate.now ()).forEach (reservationEntity -> {
                if (reservationEntity.getStartDate().isBefore(LocalDate.now())) {
                    reservationEntity.setStatus (ReservationStatus.CANCELLED);
                    reservationRepository.save (reservationEntity);

                    space.setAvailable (true);
                }
            });
        });
    }

    public List<Object> findAvailableSpaceDtoByFloorAndDate(SpaceFloorDateDto spaceFloorDateDto){

        return reservationRepository.findSpaceAvailableDto(spaceFloorDateDto.getFloor(), spaceFloorDateDto.getStartDate());
    }
}
