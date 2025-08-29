package com.proj.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private UUID roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    // Getters and setters
    public UUID getRoleId() { return roleId; }
    public void setRoleId(UUID roleId) { this.roleId = roleId; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
}