package com.proj.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proj.filter.JwtAuthFilter;

@Configuration
public class GatewayConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/user-service/**")
            		.filters(f -> f.filter(jwtAuthFilter)) 
                .uri("lb://USER-SERVICE1"))
            .route("question-service", r -> r.path("/question-service/**")
            		.filters(f -> f.filter(jwtAuthFilter)) 
                .uri("lb://QUESTION-SEVICSE"))
            
            .route("quiz-service", r -> r.path("/quiz-service/**")
            		.filters(f -> f.filter(jwtAuthFilter)) 
                .uri("lb://QUIZ-SERVICE"))
            .route("contest-service", r -> r.path("/contest-service/**")
            		.filters(f -> f.filter(jwtAuthFilter)) 
            		.uri("lb://CONTEST-SERVICE"))
            .build();
    }
}