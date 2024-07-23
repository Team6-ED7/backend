package com.spaceplanner.booking.space.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spaces")
public class Space {
        @Id
        @GeneratedValue(strategy= GenerationType.SEQUENCE)
        private Long Id;
        private String name;
        private int floor;
        private String description;
        private boolean available;

        @Column(name = "code_uuid")
        private UUID codeUuid;

        //Genera el código UUID antes de guardar en la base de datos.
        @PrePersist
        private void generateUuidCode() {
                codeUuid = UUID.randomUUID();
        }


}
