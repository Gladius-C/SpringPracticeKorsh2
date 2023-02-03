package com.example.begin.controllers.services;

import com.example.begin.controllers.Repository.UserRepository;
import com.example.begin.controllers.models.User;

import com.example.begin.controllers.models.UserDetailsPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        return new UserDetailsPrincipal(user);
    }
}