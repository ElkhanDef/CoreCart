package com.onlinestore.corecart.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/logout").permitAll()
                                .requestMatchers("/v1/products/public/**").permitAll()
                                .requestMatchers("/v1/users/public/**").permitAll()
                                .requestMatchers(
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html"
                                ).permitAll()
                                .requestMatchers(HttpMethod.GET, "/v1/products").permitAll()
                                .requestMatchers("/v1/payments/success", "/v1/payments/cancel").permitAll()
                                .requestMatchers("/v1/cart/**").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers("/v1/payments/**").hasAnyRole("CUSTOMER", "ADMIN")
                                .anyRequest().permitAll()
                );


        http.formLogin(form
                -> form.permitAll()
                .successHandler((request, response, authentication) ->
                        response.setStatus(HttpServletResponse.SC_OK))
                .failureHandler((request, response, exception) ->
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)));

        http.exceptionHandling(ex ->
                ex.authenticationEntryPoint((request, response, exception) ->
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN)));

        http.logout(logout -> logout.permitAll());


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}
