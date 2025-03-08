package com.rammy.springSecurity.Authentication.security;

import com.rammy.springSecurity.Authentication.entities.User;
import com.rammy.springSecurity.Authentication.exceptions.ResourceNotFoundException;
import com.rammy.springSecurity.Authentication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                ()->new ResourceNotFoundException("User not found with Email: "+username)
        );
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("user not found with id: "+userId)
        );
    }
}
