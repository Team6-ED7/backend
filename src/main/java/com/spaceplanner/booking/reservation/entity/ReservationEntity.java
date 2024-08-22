package com.spaceplanner.booking.reservation.entity;

import com.spaceplanner.booking.space.entity.Space;
import com.spaceplanner.booking.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Data

public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;

    private LocalDate startDate;


    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

}
