package com.spaceplanner.booking.reservation.repository;

import com.spaceplanner.booking.reservation.entity.ReservationEntity;
import com.spaceplanner.booking.reservation.entity.dto.SpaceAvailableDto;
import com.spaceplanner.booking.space.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<ReservationEntity, Long> {

    String FILTER_FLOOR_AND_DATE = "SELECT s.id, s.name, s.floor, s.available FROM spaces s WHERE s.floor=:floor UNION SELECT s.id, s.name, s.floor, s.available FROM spaces s WHERE s.id IN (SELECT r.space_id FROM reservation r WHERE r.start_date=:startDate);";

    List<ReservationEntity> findAllBySpaceAndStartDate(Space space, LocalDate startDate);

    @Query("SELECT r.space.name FROM ReservationEntity r WHERE r.user.email = :userId")
    List<String> findAllBy_Email(String userId);

    List<ReservationEntity> findAllBySpace_Id(Long spaceId);

    @Query(value = FILTER_FLOOR_AND_DATE, nativeQuery = true)
    List<Object> findSpaceAvailableDto(@Param("floor") Integer floor, @Param("startDate") LocalDate startDate);

}
