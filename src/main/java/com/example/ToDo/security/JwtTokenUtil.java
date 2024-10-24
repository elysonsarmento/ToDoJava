package com.example.ToDo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(UserDetails userDetails) {
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        return JWT.create()
                .withSubject(customUserDetails.getUsername())
                .withClaim("userId", customUserDetails.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public String extractEmail(String token) {
        token = removeBearerPrefix(token);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secret.getBytes())).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        token = removeBearerPrefix(token);
        String username = extractEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        token = removeBearerPrefix(token);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secret.getBytes())).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getExpiresAt().before(new Date());
    }

    public String getUsernameFromToken(String token) {
        token = removeBearerPrefix(token);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secret.getBytes())).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    private String removeBearerPrefix(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}