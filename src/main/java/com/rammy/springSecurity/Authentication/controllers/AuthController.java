package com.rammy.springSecurity.Authentication.controllers;

import com.rammy.springSecurity.Authentication.dto.LoginDTO;
import com.rammy.springSecurity.Authentication.dto.LoginResponseDTO;
import com.rammy.springSecurity.Authentication.dto.SignUpDTO;
import com.rammy.springSecurity.Authentication.dto.UserDTO;
import com.rammy.springSecurity.Authentication.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        return new ResponseEntity<>(authService.signUp(signUpDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpServletResponse){
        String[] tokens = authService.login(loginDTO);
        Cookie cookie = new Cookie("refreshToken",tokens[1]);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<UserDTO> getProfile(){
        return ResponseEntity.ok(authService.getProfile());
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
        String refreshToken= Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getValue()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found inside the Cookies"));
        String[] tokens = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }
}
