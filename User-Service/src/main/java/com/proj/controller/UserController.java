package com.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proj.model.Users;
import com.proj.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody Users user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return userService.Verify(user);
    }

    @PostMapping("/refresh")
    public String refreshToken(@RequestBody String refreshToken) {
        return userService.refreshToken(refreshToken);
    }

    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }
    // old way have to edit this to make it independent to JWTFIlter
// NEW WAY - UserController.java
//    @GetMapping("/users/me")
//    public Users getCurrentUser(@RequestHeader("X-User-Id") UUID userId) { // (1)
//        return userService.getUserById(userId); // (2)
//    }
    @GetMapping("/users/me")
    public Users getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable UUID id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}