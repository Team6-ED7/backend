package com.spaceplanner.booking.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String lastName;

    @Column(unique=true)
    private String email;

    private String hashedPassword;

    @Column(name = "is_enabled")
    @Builder.Default
    private boolean isEnabled = true;

    @Column(name = "account_no_expired")
    @Builder.Default
    private boolean accountNoExpired = true;

    @Column(name = "account_no_locked")
    @Builder.Default
    private boolean accountNoLocked = false;

    @Column(name = "credential_No_Expired" )
    @Builder.Default
    private boolean credentialNoExpired= false;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RoleEnum roleEnum = RoleEnum.USER;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

}
