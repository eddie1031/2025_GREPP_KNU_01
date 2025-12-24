package io.eddie.ecommerce.domain.auth.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.MapType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Slf4j
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

    public boolean validate(String token) {

        try {
            getClaims(token);

            return true;
        } catch ( JwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
//            e.printStackTrace();
        } catch ( IllegalStateException e) {
            log.error("JWT token compact of handler are invalid. {}", e.getMessage());
        } catch ( Exception e ) {
            log.error("An error occurred during JWT token validation: {}", e.getMessage());
        }

        return false;
    }

    public Map<String, Object> getClaims(String token) {

        JwtParser parser = Jwts.parser()
                .verifyWith(getKey())
                .build();

        return parser.parseSignedClaims(token)
                .getPayload();

    }


    private @NonNull SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
