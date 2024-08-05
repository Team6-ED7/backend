package com.spaceplanner.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/api/users/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/users/login").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/typespaces/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/spaces/register").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/spaces").permitAll();

                    auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();  // Allow public access to Swagger UI

                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())  // Use basic auth, replace with JWT or OAuth2 for production
                .build();
    }
}
