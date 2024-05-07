package com.wheelzhub.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()  // Swagger UI
                                .requestMatchers("/api/user-register/**", "/api/user-login/**").permitAll()  // Public APIs
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")  // Admin-only APIs
                                .anyRequest().authenticated()  // All other requests need authentication
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
        ;

        return http.build();
    }
}
