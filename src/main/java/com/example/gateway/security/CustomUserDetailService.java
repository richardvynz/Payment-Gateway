package com.example.gateway.security;


import com.example.gateway.model.Users;
import com.example.gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
@JsonComponent
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email).orElse(null);
        if (users == null) throw new UsernameNotFoundException("User not found");
        return new org.springframework.security.core.userdetails.User(
                users.getEmail(), users.getPassword(), Collections.singleton(
                        new SimpleGrantedAuthority(users.getRole())));
    }
}
