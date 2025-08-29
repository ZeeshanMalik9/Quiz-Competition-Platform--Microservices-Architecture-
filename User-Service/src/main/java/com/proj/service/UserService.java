package com.proj.service;

import com.proj.model.Users;
import com.proj.model.RefreshToken;
import com.proj.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    // Register a new user
    public String register(Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent() || usersRepository.findByUsername(user.getUsername()).isPresent()) {
            return "User already exists";
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setStatus("ACTIVE");
        user.setCreatedAt(java.time.LocalDateTime.now());
        usersRepository.save(user);
        // Generate and save refresh token
        String refreshToken = refreshTokenService.createToken(user.getUserId()).getToken();
        return "User registered successfully. Refresh Token: " + refreshToken;
    }

    // Login/Verify user
    public String Verify(Users user) {
        Optional<Users> dbUser = usersRepository.findByEmail(user.getEmail());
        if (dbUser.isPresent() && passwordEncoder.matches(user.getPasswordHash(), dbUser.get().getPasswordHash())) {
            // Use username for token generation and verification
            String username = dbUser.get().getUsername();
            // Generate JWT token
            String token = jwtService.generateToken(dbUser.get());
            // Generate and save refresh token
            String refreshToken = refreshTokenService.createToken(dbUser.get().getUserId()).getToken();
            return "JWT: " + token + ", Refresh Token: " + refreshToken;
        }
        return "Invalid credentials";
    }

  //   Refresh token (stub)
    public String refreshToken(String refreshToken) {
        // Validate the refresh token
        if (!refreshTokenService.validateToken(refreshToken)) {
            return "Invalid or expired refresh token";
        }
        // Find the user associated with the refresh token
        RefreshToken tokenEntity = refreshTokenService.findByToken(refreshToken);
        if (tokenEntity == null) {
            return "Refresh token not found";
        }
        // Generate new JWT token for the user
        Users user = usersRepository.findById(tokenEntity.getUserId()).orElse(null);
        if (user == null) {
            return "User not found";
        }
        String newJwt = jwtService.generateToken(user);
        return newJwt;
    }

    public Users getUserById(UUID id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users getCurrentUser() {
        // Get current user from SecurityContext
        Object principal = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Optional<Users> user = usersRepository.findByUsername(username);
            return user.orElse(null);
        }
        return null;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public String updateUser(UUID id, Users user) {
        Optional<Users> dbUser = usersRepository.findById(id);
        if (dbUser.isPresent()) {
            Users u = dbUser.get();
            u.setUsername(user.getUsername());
            u.setEmail(user.getEmail());
            u.setStatus(user.getStatus());
            usersRepository.save(u);
            return "User updated";
        }
        return "User not found";
    }

    public String deleteUser(UUID id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return "User deleted";
        }
        return "User not found";
    }
}