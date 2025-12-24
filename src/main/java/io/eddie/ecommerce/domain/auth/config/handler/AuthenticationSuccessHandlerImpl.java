package io.eddie.ecommerce.domain.auth.config.handler;

import io.eddie.ecommerce.domain.auth.model.dto.MemberDetails;
import io.eddie.ecommerce.domain.auth.service.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
            , HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {

        MemberDetails details = (MemberDetails) authentication.getPrincipal();

        Map<String, Object> body = new HashMap<>();

        String username = details.getUsername();

        body.put("username", username);

        String token = jwtTokenProvider.issue(body);

        response.addHeader("knu-token", token);

        response.addCookie(
                new Cookie("knu-token", token)
        );

    }
}
