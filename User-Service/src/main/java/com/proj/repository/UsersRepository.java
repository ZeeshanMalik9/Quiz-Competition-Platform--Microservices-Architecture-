package com.proj.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.model.Users;

public interface UsersRepository extends JpaRepository<Users, UUID>{

	Optional<Users> findByEmail(String email);

	Optional<Users> findByUsername(String username);

}
