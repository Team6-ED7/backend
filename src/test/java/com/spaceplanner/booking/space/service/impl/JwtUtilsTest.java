/*
package com.spaceplanner.booking.space.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.spaceplanner.booking.Global.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void testCreateToken() {
        UserDetails user = User.withUsername("testUser")
                               .password("password")
                               .authorities("ROLE_USER")
                               .build();
                               
        String token = jwtUtils.createToken(user);

        assertNotNull(token);
        
        DecodedJWT decodedJWT = jwtUtils.validateToken(token);

        assertNotNull(decodedJWT);
        
        assertEquals("testUser", jwtUtils.extractUsername(decodedJWT));
    }
}*/
