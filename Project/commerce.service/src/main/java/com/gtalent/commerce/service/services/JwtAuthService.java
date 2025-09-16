package com.gtalent.commerce.service.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.LoginRequest;

@Service
public class JwtAuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public String register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(LoginRequest request){
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                return jwtService.generateToken(user);
            }
        }
        throw new RuntimeException("Invalid token.");
    }
}
