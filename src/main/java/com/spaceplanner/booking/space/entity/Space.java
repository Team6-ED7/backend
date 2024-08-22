package com.spaceplanner.booking.space.entity;

import com.spaceplanner.booking.typespace.entity.TypeSpace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spaces")
@Builder
public class Space {

        @Id
        @GeneratedValue(strategy= GenerationType.SEQUENCE)
        private Long id;
        private String name;
        private Integer floor;
        private String description;
        private Integer capacity;
        private Boolean available;

        @ManyToOne
        @JoinColumn(name = "type_space_id")
        private TypeSpace typeSpace;

}
