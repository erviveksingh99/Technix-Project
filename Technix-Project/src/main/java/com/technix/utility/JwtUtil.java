package com.technix.utility;

import java.sql.Date;

import com.technix.custome.TokenNotFoundException;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    private String secret = "b3Aad91mB0fYo2lK3xWnS7eVmQ5GhLpX0YzQ7JzN7O3B6aPbT";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) // 30 days
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser().
                    setSigningKey(secret).
                    parseClaimsJws(token).
                    getBody().
                    getSubject();
        } catch (TokenNotFoundException e) {
            throw new TokenNotFoundException("Valid token is required");
        }
    }

    public boolean validateToken(String token, String username) {
        try {
            return username.equals(extractUsername(token)) && !isTokenExpired(token);
        } catch (TokenNotFoundException e) {
            throw new TokenNotFoundException("Valid token is required");
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            return Jwts.parser().
                    setSigningKey(secret).
                    parseClaimsJws(token).
                    getBody().
                    getExpiration().
                    before(new java.util.Date());
        } catch (TokenNotFoundException e) {
            throw new TokenNotFoundException("Valid token is required");
        }
    }
}



