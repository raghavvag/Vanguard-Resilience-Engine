package org.example.backend.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.example.backend.auth.entity.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final SecretKey key =
            Keys.hmacShaKeyFor("change-this-to-a-very-long-secure-secret-key"
                    .getBytes());

    private final long EXPIRATION_MS=24*60*60*1000;

    public  String generateToken(User user){
        Date now=new Date();
        Date expiry=new Date(now.getTime()+EXPIRATION_MS);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role",user.getRole().name())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }
    public String getEmailFromToken(String token){
        return parseClaims(token).getBody().getSubject();
    }
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

}
