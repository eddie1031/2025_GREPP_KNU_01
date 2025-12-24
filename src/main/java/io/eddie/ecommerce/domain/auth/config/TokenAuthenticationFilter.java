package io.eddie.ecommerce.domain.auth.config;

import io.eddie.ecommerce.domain.auth.service.JwtTokenProvider;
import io.eddie.ecommerce.domain.auth.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        if ( bearerToken != null && bearerToken.startsWith("Bearer ") ) {

            // Bearer ey.saifohweiowaefio;sadfhioajwf
            String token = bearerToken.substring(7);

            if ( token.isBlank() && jwtTokenProvider.validate(token)) {

                Map<String, Object> claims = jwtTokenProvider.getClaims(token);

                String username = claims.get("username").toString();

                UserDetails memberDetails = memberService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                            memberDetails,
                            null,
                            memberDetails.getAuthorities()
                    );

                SecurityContextHolder.getContext()
                        .setAuthentication(authenticationToken);

            }

        }

        filterChain.doFilter(request, response);

    }

}
