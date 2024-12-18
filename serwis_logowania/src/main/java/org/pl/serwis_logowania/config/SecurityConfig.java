package org.pl.serwis_logowania.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/login","/auth/register", "/auth/users","/auth/verifytoken")
                        .permitAll()
                        .anyRequest().authenticated()
                ).csrf(csrf -> csrf.disable());
        return http.build();
    }
}

