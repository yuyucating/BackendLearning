package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // 把 products 攔下來、需要驗證, 其他不需要驗證
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authz -> authz
        .requestMatchers("/jwt/**").permitAll() // 都不會被攔截
        .requestMatchers("/session/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/v1/products/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/v2/users/**").permitAll()
        .anyRequest().authenticated()
        
        // .requestMatchers(HttpMethod.DELETE, "/v1/products/**").authenticated() // 攔截
        // .requestMatchers(HttpMethod.POST, "/v1/products/**").authenticated()
        // .requestMatchers(HttpMethod.PUT, "/v1/products/**").authenticated()
        // .requestMatchers(HttpMethod.DELETE, "/v2/users/**").authenticated() // 攔截
        // .requestMatchers(HttpMethod.POST, "/v2/users/**").authenticated()
        // .requestMatchers(HttpMethod.PUT, "/v2/users/**").authenticated()
        // .anyRequest().permitAll()
        ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //用 jwtAuthFilter 檢查驗證

        return httpSecurity.build();

        /* 設定檔可以視為
         * httpSecurity.csrf.disable=true
         * httpSecurity.permit.urls=/products/**, /users/**
         * httpSecurity.filter.before=src/main/java/com/example/com/config/jwtAuthFilter.java
        */
    }
}


// SecurityConfig 固定寫法? 上網查再修一下就好了?