package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.requests.LoginRequest;
import com.example.demo.requests.RegisterUserRequest;
import com.example.demo.responses.AuthResponse;
import com.example.demo.responses.UserResponse;
import com.example.demo.services.AuthService;


@RestController
@RequestMapping("/jwt")
@CrossOrigin("*")
public class JwtAuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterUserRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.auth(request));
    }

}
