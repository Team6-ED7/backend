package com.spaceplanner.booking.config;

import com.spaceplanner.booking.Global.util.JwtUtils;
import com.spaceplanner.booking.user.service.impl.UserDetailServiceImpl;
import net.bytebuddy.asm.AsmVisitorWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.method.PrePostTemplateDefaults;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.PasswordManagementDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> {
/*                    auth.requestMatchers(HttpMethod.POST, "/api/users/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/users/login").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/typespaces/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/spaces/register").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/spaces/massive-register").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/spaces/available/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/spaces/floor/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/spaces/filter").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/spaces/small-spaces").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/spaces").permitAll();*/


                    auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();  // Allow public access to Swagger UI

                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
     PrePostTemplateDefaults prePostTemplateDefaults() {
        return new PrePostTemplateDefaults();
    }
}
