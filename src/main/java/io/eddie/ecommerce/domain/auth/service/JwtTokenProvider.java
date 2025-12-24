package io.eddie.ecommerce.domain.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenProvider {

    public String issue() {

        long expTime = 60_000L;

        String secretKey = "A5A10BC17B4E653C78BDD55FFFD9DEE3BE5790CD6F3F64DC1E82E791F9821C17";

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String jwtToken = Jwts.builder()
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                new Date().getTime() + expTime
                        )
                ).signWith(key)
                .compact();


        return jwtToken;
    }

}
