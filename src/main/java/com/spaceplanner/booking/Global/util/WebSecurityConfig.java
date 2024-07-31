package com.spaceplanner.booking.Global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private final JwtAuthConverterProperties jwtAuthConverterProperties;
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF para aplicaciones RESTful
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/users/register").permitAll() // Permitir registro de usuarios
                        .requestMatchers(HttpMethod.POST, "/users/login").permitAll() // Permitir login de usuarios
                        .requestMatchers(HttpMethod.POST, "/typespaces/register").hasRole("ADMIN") // Solo administradores pueden registrar tipos de espacio
                        .requestMatchers(HttpMethod.POST, "/spaces/register").hasRole("ADMIN") // Solo administradores pueden registrar espacios
                        .requestMatchers(HttpMethod.GET, "/spaces").hasAnyRole("USER", "ADMIN") // Usuarios y administradores pueden ver los espacios
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permitir acceso a Swagger UI
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)
                        )
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
