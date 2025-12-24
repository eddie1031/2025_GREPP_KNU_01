package io.eddie.ecommerce.domain.auth.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTests {

    JwtTokenProvider tokenProvider;

    @BeforeEach
    void init() {
        tokenProvider = new JwtTokenProvider();
    }

    @Test
    void it_will_return_available_jwt() {

        Map<String, Object> body = Map.of("key", "value");

        String token = tokenProvider.issue(body);

        System.out.println("token = " + token);

        Assertions.assertNotNull(token);

    }


}