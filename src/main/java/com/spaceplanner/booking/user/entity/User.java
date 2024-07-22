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
    private Long Id;
    private String name;
    private String lastName;
    private String email;
    private String Password;

    private Rol Rol;

}
