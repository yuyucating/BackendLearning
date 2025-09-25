package com.gtalent.commerce.service.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
        .requestMatchers("/jwt/**").permitAll()
        .requestMatchers(HttpMethod.POST,"/v1/users/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/v1/users/{id}").hasAnyRole("ADMIN", "USER")
        .requestMatchers(HttpMethod.GET,"/v1/users/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,"/v1/users/**").hasRole("USER")
        .requestMatchers(HttpMethod.DELETE,"/v1/users/{id}").hasAnyRole("USER", "ADMIN")
        .requestMatchers(HttpMethod.DELETE,"/v1/users/**").hasRole("ADMIN")        
        .requestMatchers(HttpMethod.POST,"/v1/products/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/v1/products/**").permitAll()
        .requestMatchers(HttpMethod.PUT,"/v1/products/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE,"/v1/products/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,"/v1/categories/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,"/v1/categories/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/v1/categories/**").permitAll()
        .requestMatchers(HttpMethod.DELETE,"/v1/categories/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST,"/v1/orders/**").hasRole("USER")
        .requestMatchers(HttpMethod.GET,"/v1/orders/{id}").hasAnyRole("USER", "ADMIN")
        .requestMatchers(HttpMethod.GET,"/v1/orders/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,"/v1/orders/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE,"/v1/orders/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST, "/v1/reviews/**").hasRole("USER")
        .requestMatchers(HttpMethod.PUT, "/v1/reviews/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/v1/reviews/**").permitAll()
        .requestMatchers("/v1/segments/**").hasRole("ADMIN")
        // .requestMatchers("/v1/users/**","/v1/products/**","/v1/categories/**","/v1/orders/**","/v1/reviews/**").permitAll()
        .anyRequest().authenticated()
        ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //用 jwtAuthFilter 檢查驗證

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //常用的加密機制
    }

}
