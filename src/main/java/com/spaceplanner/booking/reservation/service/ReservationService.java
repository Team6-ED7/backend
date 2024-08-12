package com.spaceplanner.booking.reservation.service;

import com.spaceplanner.booking.reservation.entity.ReservationEntity;
import com.spaceplanner.booking.reservation.entity.ReservationStatus;
import com.spaceplanner.booking.reservation.entity.dto.ReservationDto;
import com.spaceplanner.booking.reservation.repository.IResorvationRepository;
import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.space.repository.ISpaceRepository;
import com.spaceplanner.booking.user.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service

public class ReservationService {

    @Autowired
    private IResorvationRepository reservationRepository;

    @Autowired
    private ISpaceRepository spaceRepository;

    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public ReservationEntity createReservation(ReservationDto reservationDto) {
        // Validate the reservation data
        validateReservationData(reservationDto);

        // Check space availability
        Space space = spaceRepository.findById(reservationDto.getSpaceId())
                .orElseThrow(() -> new RuntimeException("Space not found"));
        if (!isSpaceAvailable(space, LocalDateTime.parse(reservationDto.getStartTime()), LocalDateTime.parse(reservationDto.getEndTime()))) {
            throw new RuntimeException("Space is not available for the requested time.");
        }

        // Create a new reservation entity
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setUser(userRepository.findById(reservationDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        reservationEntity.setSpace(space);
        reservationEntity.setStartTime(LocalDateTime.parse(reservationDto.getStartTime()));
        reservationEntity.setEndTime(LocalDateTime.parse(reservationDto.getEndTime()));
        reservationEntity.setStatus(ReservationStatus.PENDING);

        // Save the reservation to the repository
        return reservationRepository.save(reservationEntity);
    }

    public boolean isSpaceAvailable(Space space, LocalDateTime startTime, LocalDateTime endTime) {
        // Check if the space is available for the requested time
        return reservationRepository.findAllBySpaceAndStartTimeBetweenOrEndTimeBetween(space, startTime, endTime, startTime, endTime).isEmpty();
    }

    private void validateReservationData(ReservationDto reservationDto) {
        // Validate that the start time is before the end time
        LocalDateTime startTime = LocalDateTime.parse(reservationDto.getStartTime());
        LocalDateTime endTime = LocalDateTime.parse(reservationDto.getEndTime());
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }

        // Additional validation logic as needed
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
