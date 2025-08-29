package com.proj.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
}