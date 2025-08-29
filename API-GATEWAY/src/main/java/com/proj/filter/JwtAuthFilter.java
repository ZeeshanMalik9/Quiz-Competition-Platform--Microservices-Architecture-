package com.proj.filter;

import com.proj.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GatewayFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	// get path of the request
        String path = exchange.getRequest().getURI().getPath();
        // Allow public endpoints
        if (path.contains("/auth/login") || path.contains("/auth/register")) {
        	// if the path contains /auth/login or /auth/register then allow the request
            return chain.filter(exchange);
        }
        
        // Get the Authorization header
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        // If the header is missing or doesn't start with "Bearer ", return 401 Unauthorized
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        	// set status code to 401
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            // complete the response
            return exchange.getResponse().setComplete();
        }
        
        // Extract the token from the header
        String token = authHeader.substring(7);
        try {
        	// validate the token
            var claims = jwtUtil.validateTokenAndGetClaims(token);
            // Add user details to the request headers
            var request = exchange.getRequest().mutate()
            	// add user id, username and roles to the header
                .header("X-User-Id", claims.getSubject())
                .header("X-Username", claims.get("username", String.class))
                .header("X-Roles", claims.get("roles").toString())
                .build();
            // continue the filter chain with the modified request
            return chain.filter(exchange.mutate().request(request).build());
        } catch (Exception e) {
        	// If token validation fails, return 401 Unauthorized
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}