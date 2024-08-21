package com.spaceplanner.booking.user.entity.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor


public class UserLoginResponse {
    private String token;
    private String name;

    public UserLoginResponse (String jwtToken, String name) {
    }
}
