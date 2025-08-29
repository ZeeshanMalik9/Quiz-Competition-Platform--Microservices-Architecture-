package com.proj.service;

import com.proj.model.Role;
import com.proj.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(UUID id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

    public Role updateRole(UUID id, Role role) {
        Optional<Role> dbRole = roleRepository.findById(id);
        if (dbRole.isPresent()) {
            Role r = dbRole.get();
            r.setRoleName(role.getRoleName());
            return roleRepository.save(r);
        }
        return null;
    }

    public void deleteRole(UUID id) {
        roleRepository.deleteById(id);
    }
}