package com.example.posts.services.impl;

import com.example.posts.dtos.SignUpDto;
import com.example.posts.entities.Users;
import com.example.posts.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public Optional<Users> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
