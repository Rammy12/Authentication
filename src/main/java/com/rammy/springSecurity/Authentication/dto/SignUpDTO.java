package com.rammy.springSecurity.Authentication.dto;

import com.rammy.springSecurity.Authentication.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
}
