package com.spaceplanner.booking.reservation.repository;

import com.spaceplanner.booking.reservation.entity.ReservationEntity;
import com.spaceplanner.booking.space.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IResorvationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findAllBySpaceAndStartTimeBetweenOrEndTimeBetween(Space space, LocalDateTime startTime1, LocalDateTime endTime1, LocalDateTime startTime2, LocalDateTime endTime2);

    List<ReservationEntity> findAllByUser_Id(Long userId);

    List<ReservationEntity> findAllBySpace_Id(Long spaceId);

}
