package io.eddie.ecommerce.domain.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtTokenProvider {

    @Value("${jwt.expiration}")
    private long expTime;

    @Value("${jwt.secret.app-key}")
    private String secretKey;

    public String issue(Map<String, Object> body) {

        SecretKey key = getKey();

        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                new Date().getTime() + expTime
                        )
                )
                .claims(body)
                .signWith(key)
                .compact();
    }

    private @NonNull SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
