package com.proj.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

import org.springframework.web.server.WebFilter;

// redundent filter for JWT token validation so remove it

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private JWTFilter jwtFilter;
        
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/USER-SERVICE/auth/login", "/USER-SERVICE/auth/register").permitAll()
                .anyExchange().authenticated()
            )
            .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        .build();
    }
}