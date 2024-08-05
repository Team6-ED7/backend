package com.spaceplanner.booking.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String lastName;

    @Column(unique=true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USER;

}
