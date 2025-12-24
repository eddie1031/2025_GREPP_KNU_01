package io.eddie.ecommerce.domain.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConf {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf( csrf -> {
                    csrf.disable();
                })

                .headers(
                        headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )

                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(HttpMethod.POST, "/members")
                                .permitAll()

                                .requestMatchers("/h2-console")
                                    .permitAll()

                                .requestMatchers("/members/**")
                                    .hasAnyAuthority("MEMBER", "ADMIN")

                                .anyRequest()
                                    .permitAll()
                )
                .build();

    }

}
