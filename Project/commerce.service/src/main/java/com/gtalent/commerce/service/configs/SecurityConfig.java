package com.gtalent.commerce.service.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //定義安全性設定黨
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // 把 products 攔下來、需要驗證, 其他不需要驗證
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authz -> authz
        .requestMatchers("/jwt/**").permitAll() // 都不會被攔截
        .requestMatchers("/swagger-ui/**","/v3/api-docs/**/", "/swagger-ui.html").permitAll()
        .anyRequest().authenticated()
        ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //用 jwtAuthFilter 檢查驗證

        return httpSecurity.build();

        /* 設定檔可以視為
         * httpSecurity.csrf.disable=true
         * httpSecurity.permit.urls=/products/**, /users/**
         * httpSecurity.filter.before=src/main/java/com/example/com/config/jwtAuthFilter.java
        */
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //常用的加密機制
    }

}
