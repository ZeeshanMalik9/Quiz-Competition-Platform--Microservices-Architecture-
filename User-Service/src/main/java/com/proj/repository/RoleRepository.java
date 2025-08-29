package com.proj.repository;

import com.proj.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    // You can add custom query methods here if needed
}