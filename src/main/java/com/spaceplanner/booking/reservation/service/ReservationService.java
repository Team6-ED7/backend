package com.spaceplanner.booking.reservation.service;

import com.spaceplanner.booking.reservation.entity.ReservationEntity;
import com.spaceplanner.booking.reservation.entity.ReservationStatus;
import com.spaceplanner.booking.reservation.entity.dto.ReservationDto;
import com.spaceplanner.booking.reservation.repository.IResorvationRepository;
import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.repository.ISpaceRepository;
import com.spaceplanner.booking.user.repository.IUserRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service

public class ReservationService {

    private final IResorvationRepository reservationRepository;

    private final ISpaceRepository spaceRepository;

    private final IUserRepository userRepository;

    public ReservationService(IResorvationRepository reservationRepository, ISpaceRepository spaceRepository, IUserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public ReservationEntity createReservation(ReservationDto reservationDto) {

        validateReservationData(reservationDto);
        Space space = spaceRepository.findById(reservationDto.getSpaceId())
                .orElseThrow(() -> new RuntimeException("Space not found"));
        if (!isSpaceAvailable(space, LocalDateTime.parse(reservationDto.getStartTime()), LocalDateTime.parse(reservationDto.getEndTime()))) {
            throw new RuntimeException("Space is not available for the requested time.");
        }

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setUser(userRepository.findById(reservationDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        reservationEntity.setSpace(space);
        reservationEntity.setStartTime(LocalDateTime.parse(reservationDto.getStartTime()));
        reservationEntity.setEndTime(LocalDateTime.parse(reservationDto.getEndTime()));
        reservationEntity.setStatus(ReservationStatus.PENDING);


        return reservationRepository.save(reservationEntity);
    }

    public boolean isSpaceAvailable(Space space, LocalDateTime startTime, LocalDateTime endTime) {
        // Check if the space is available for the requested time
        return reservationRepository.findAllBySpaceAndStartTimeBetweenOrEndTimeBetween(space, startTime, endTime, startTime, endTime).isEmpty();
    }

    private void validateReservationData(ReservationDto reservationDto) {

        LocalDateTime startTime = LocalDateTime.parse(reservationDto.getStartTime());
        LocalDateTime endTime = LocalDateTime.parse(reservationDto.getEndTime());
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }


    }

    public Optional<ReservationEntity> findById(Long id) {

        return reservationRepository.findById(id);
    }
    public List<ReservationEntity> findByUserId(Long userId) {

        return reservationRepository.findAllByUser_Id(userId);
    }
    public List<ReservationEntity> findBySpaceId(Long spaceId) {
        return reservationRepository.findAllBySpace_Id(spaceId);
    }
}
