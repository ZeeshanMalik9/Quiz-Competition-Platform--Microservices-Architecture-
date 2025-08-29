package com.proj.service;

import com.proj.model.RefreshToken;
import com.proj.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    // Create a new refresh token for a user
    public RefreshToken createToken(UUID userId) {
        RefreshToken token = new RefreshToken();
        token.setUserId(userId);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusDays(7)); // 7 days validity
        return refreshTokenRepository.save(token);
    }

    // Validate a refresh token
    public boolean validateToken(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        return refreshToken.isPresent() && refreshToken.get().getExpiryDate().isAfter(LocalDateTime.now());
    }

    // Find a refresh token entity by token string
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElse(null);
    }

    // Revoke (delete) a refresh token by id
    public void revokeToken(UUID id) {
        refreshTokenRepository.deleteById(id);
    }
}