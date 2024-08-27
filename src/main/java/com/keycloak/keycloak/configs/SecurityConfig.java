package com.keycloak.keycloak.configs;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.keycloak.keycloak.JavaAuthConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JavaAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.csrf(csrf->csrf.disable());

        http.authorizeHttpRequests(request->request.anyRequest().permitAll());

        http.oauth2ResourceServer(token->token.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter)));
        // http.oauth2Login(Customizer.withDefaults());
        // http.oauth2Client(Customizer.withDefaults());

        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
  
}
