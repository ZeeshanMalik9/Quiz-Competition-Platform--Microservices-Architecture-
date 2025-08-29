package com.proj.service;

import com.proj.model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

@Service
public class JWTService {

    @Value("${jwt.secret:defaultsecretkeydefaultsecretkey}")
    private String jwtSecret;

    @Value("${jwt.expiration:3600000}") // 1 hour default
    private long jwtExpirationMs;

    public String generateToken(Users user) {
    	// jwtSecret string conveted to SecretKey object using Keys.hmacShaKeyFor
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        // Build the JWT token with user details and sign it with the secret key
        return Jwts.builder()
        		// Set the subject as the user's ID
                .setSubject(user.getUserId().toString())
                // Add custom claims like username, email, and roles
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("roles", user.getRoles())
                // Set issued at and expiration dates
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                // most important part: sign the token with the secret key and HS256 algorithm
                .signWith(key, SignatureAlgorithm.HS256)
                // Build the final token string
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            Jwts.parser()
            .verifyWith(key) // secretKey is a java.security.Key
            .build()
            .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.parser()
                .verifyWith(key) // key is a java.security.Key
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Set<String> getRolesFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        Object roles = Jwts.parser()
                .verifyWith(key) // key is java.security.Key
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles");
        return roles instanceof Set ? (Set<String>) roles : null;
    }

    public String extractUserName(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
    }

    public boolean ValidateToken(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        return (username != null && username.equals(userDetails.getUsername()) && validateToken(token));
    }
}