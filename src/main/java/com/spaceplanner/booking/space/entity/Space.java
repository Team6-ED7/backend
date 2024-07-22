package com.spaceplanner.booking.space.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spaces")
@Data
@NoArgsConstructor
public class Space {
        @Id
        @GeneratedValue(strategy= GenerationType.SEQUENCE)
        private Long Id;
        private String name;
        private int floor;
        private String description;
        private boolean available;


}
