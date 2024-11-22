package com.Library.E_Library.util;

import com.Library.E_Library.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "my-secret-key-my-secret-key-my-secret-key";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));


    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return this.createToken(claims,userDetails.getUsername());
    }



    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(Date.from(Instant.now()))
                .expiration((Date.from(Instant.now().plus(10, ChronoUnit.HOURS))))
                .signWith(key)
                .compact();
    }


    public String extractUsername(String token){
        return this.extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return this.extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String username = this.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    }


    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
