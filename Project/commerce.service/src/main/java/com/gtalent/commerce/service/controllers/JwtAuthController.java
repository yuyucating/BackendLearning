package com.gtalent.commerce.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.responses.GetUserResponse;
import com.gtalent.commerce.service.requests.CreateUserRequest;
import com.gtalent.commerce.service.requests.LoginRequest;
import com.gtalent.commerce.service.responses.AuthResponse;
import com.gtalent.commerce.service.services.JwtAuthService;
import com.gtalent.commerce.service.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/jwt")
@CrossOrigin("*")
@Tag(name="JWT Authentication")
public class JwtAuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtAuthService jwtAuthService;

    @Operation(summary = "User Register", description = "Create user and return JWT Token") // 添加註解
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody CreateUserRequest request){
        User user = userService.createUser(request);
        String token = jwtAuthService.register(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(summary = "User Login", description = "User login and return JWT Token") // 添加註解
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        String token = jwtAuthService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}