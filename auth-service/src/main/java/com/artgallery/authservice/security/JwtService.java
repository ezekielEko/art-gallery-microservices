package com.artgallery.authservice.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "mySuperSecretKeyForJwtAuthentication123456";

    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 86400000))
                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY.getBytes()
                )
                .compact();
    }
}
