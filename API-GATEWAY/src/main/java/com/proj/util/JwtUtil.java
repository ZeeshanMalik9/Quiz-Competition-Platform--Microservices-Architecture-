package com.proj.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;

@Component
public class JwtUtil {

	// Inject the secret key from application properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    private Key key;

    // Initialize the secret key after the bean is constructed
    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Validate the token signature and expiration and return the claims
    public Claims validateTokenAndGetClaims(String token) {
    	// Parse the token and validate its signature using the secret key
        return Jwts.parserBuilder()
        		// Set the signing key to validate the token's signature
                .setSigningKey(key)
                // Build the parser
                .build()
                // Parse the token and get the claims (payload)
                .parseClaimsJws(token)
                // Return the claims (payload) of the token
                .getBody();
    }
}