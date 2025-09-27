package com.clinic.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET_KEY = "mySuperSecretKeyForJwtSigning";


    private static final long EXPIRATION_TIME = 1000 * 60 * 60;


    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // token subject is the email
                .setIssuedAt(new Date(System.currentTimeMillis())) // issued now
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // expires in 1h
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // sign with secret
                .compact();
    }


    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
