package com.rammy.springSecurity.Authentication.services.Impl;

import com.rammy.springSecurity.Authentication.dto.LoginDTO;
import com.rammy.springSecurity.Authentication.dto.SignUpDTO;
import com.rammy.springSecurity.Authentication.dto.UserDTO;
import com.rammy.springSecurity.Authentication.entities.User;
import com.rammy.springSecurity.Authentication.repositories.UserRepository;
import com.rammy.springSecurity.Authentication.security.JWTService;
import com.rammy.springSecurity.Authentication.security.UserService;
import com.rammy.springSecurity.Authentication.services.AuthService;
import com.rammy.springSecurity.Authentication.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;
    private final SessionService sessionService;

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());

        if (user.isPresent())
            throw new BadCredentialsException("Cannot signup, User already exists with email " + signUpDTO.getEmail());

        User mappedUser = modelMapper.map(signUpDTO, User.class);
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public String[] login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.createAccessToken(user);
        String refreshToken = jwtService.createRefreshToken(user);
        sessionService.generateNewSession(user,refreshToken);
        return new String[]{accessToken,refreshToken};
    }

    @Override
    public UserDTO getProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public String[] refreshToken(String refreshToken) {
        Long userId=jwtService.generateUserIdFromToken(refreshToken);
        sessionService.validSession(refreshToken);
        User user=userService.getUserById(userId);
        String accessToken = jwtService.createAccessToken(user);
        return new String[]{accessToken,refreshToken};
    }
}
