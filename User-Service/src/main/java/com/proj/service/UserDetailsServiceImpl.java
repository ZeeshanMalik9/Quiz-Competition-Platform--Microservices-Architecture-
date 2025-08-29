package com.proj.service;

import com.proj.model.Users;
import com.proj.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
// declare this class as a service bean
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userOpt = usersRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Users user = userOpt.get();
        // You may want to create a custom UserDetails implementation
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .authorities(user.getRoles().toArray(new String[0]))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!"ACTIVE".equals(user.getStatus()))
                .build();
    }
}