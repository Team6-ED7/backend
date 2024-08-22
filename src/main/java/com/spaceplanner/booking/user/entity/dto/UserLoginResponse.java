package com.spaceplanner.booking.user.entity.dto;


import lombok.*;


@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserLoginResponse {
    private String token;
    private String name;


}