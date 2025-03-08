package com.rammy.springSecurity.Authentication.services;

import com.rammy.springSecurity.Authentication.dto.LoginDTO;
import com.rammy.springSecurity.Authentication.dto.SignUpDTO;
import com.rammy.springSecurity.Authentication.dto.UserDTO;

public interface AuthService {
    UserDTO signUp(SignUpDTO signUpDTO);
    String[] login(LoginDTO loginDTO);
    UserDTO getProfile();
    String[] refreshToken(String refreshToken);
}
