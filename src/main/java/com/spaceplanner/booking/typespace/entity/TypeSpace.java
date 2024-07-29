package com.spaceplanner.booking.typespace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spaceplanner.booking.space.entity.Space;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "type_spaces")
public class TypeSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

//    @JsonIgnore
//    @OneToMany(mappedBy = "typeSpace")
//    private List<Space> listSpace;
}
