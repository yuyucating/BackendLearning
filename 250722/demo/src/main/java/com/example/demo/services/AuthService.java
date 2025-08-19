package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.LoginRequest;
import com.example.demo.requests.RegisterUserRequest;
import com.example.demo.responses.AuthResponse;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterUserRequest request){
        // 建立 User
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole("ROLE_USER");

        User userNew = userRepository.save(user);

        //產出 token
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
    public AuthResponse auth(LoginRequest request){
        // 找到對應 User
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if(userOptional.isPresent()){ // 如果有找到
            User user = userOptional.get();
            if(request.getPassword().equals(user.getPassword())){
                // 產出 token
                String jwtToken = jwtService.generateToken(user);
                return new AuthResponse(jwtToken);
            }
        }
        throw new RuntimeException("invalidate...");
    }

}
