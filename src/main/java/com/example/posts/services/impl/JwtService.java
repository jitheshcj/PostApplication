package com.example.posts.services.impl;

import com.example.posts.entities.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${application.secret.key.value}")
    public String KEYS_VALUE;
    private SecretKey generateKey(){
        return Keys.hmacShaKeyFor(KEYS_VALUE.getBytes(StandardCharsets.UTF_8));
    }
    public String generateAccessToken(Users users){
       return Jwts.builder()
                .subject(String.valueOf(users.getId()))
                .claim("name",users.getName())
                .claim("email",users.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(generateKey())
                .compact();
    }

    public String generateRefreshToken(Users users){
        return Jwts.builder()
                .subject(String.valueOf(users.getId()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(generateKey())
                .compact();
    }

    public String getIdFromToken(String token){
        Claims claims=Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean isValid(String accessToken) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .isSigned(accessToken);
    }
}
