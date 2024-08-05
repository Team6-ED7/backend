package com.spaceplanner.booking.space.entity;

import com.spaceplanner.booking.typespace.entity.TypeSpace;
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
        private Long id;
        private String name;
        private Integer floor;
        private String description;
        private Integer capacity;
        private Boolean available;

        @Column(name = "code_uuid")
        private UUID codeUuid;

        @ManyToOne
        @JoinColumn
        private TypeSpace typeSpace;

        //Genera el código UUID antes de guardar en la base de datos.
        @PrePersist
        private void generateUuidCode() {
                codeUuid = UUID.randomUUID();
        }


}
