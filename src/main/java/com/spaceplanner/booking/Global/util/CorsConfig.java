package com.spaceplanner.booking.Global.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    WebMvcConfigurer corsConfigurer(){

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/**")//Disponible para todas las rutas que están bajo /api/**
                        .allowedOrigins("*")//Disponible para todas URL
                        .allowedMethods("*");//Para que se pueda llamar métodos GET, POST, PUT, DELETE
            }
        };
    }
}
