package com.clinic.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String generateToken(String username) {
        // Placeholder for token generation logic
        return "token-for-" + username;
    }

    public boolean validateToken(String token) {
        // Placeholder for token validation logic
        return token != null && token.startsWith("token-for-");
    }
}
