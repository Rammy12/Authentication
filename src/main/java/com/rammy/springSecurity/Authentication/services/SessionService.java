package com.rammy.springSecurity.Authentication.services;

import com.rammy.springSecurity.Authentication.entities.User;

public interface SessionService {
    void generateNewSession(User user, String refreshToken);
    void validSession(String refreshToken);
}
