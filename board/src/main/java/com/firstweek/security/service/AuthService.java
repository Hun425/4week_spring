package com.firstweek.security.service;

import com.firstweek.security.jwt.TokenPair;

public interface AuthService {
    TokenPair login(String username);
}
