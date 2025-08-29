package com.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.proj.model.RefreshToken;
import com.proj.service.RefreshTokenService;
import java.util.UUID;

@RestController
@RequestMapping("/refresh-token")
public class RefreshTokenController {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/create")
    public RefreshToken createToken(@RequestParam UUID userId) {
        return refreshTokenService.createToken(userId);
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestBody String token) {
        return refreshTokenService.validateToken(token);
    }

    @DeleteMapping("/revoke/{id}")
    public String revokeToken(@PathVariable UUID id) {
        refreshTokenService.revokeToken(id);
        return "Refresh token revoked";
    }
}