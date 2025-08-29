package com.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.proj.model.Role;
import com.proj.service.RoleService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable UUID id) {
        return roleService.getRoleById(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable UUID id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
        return "Role deleted";
    }
}