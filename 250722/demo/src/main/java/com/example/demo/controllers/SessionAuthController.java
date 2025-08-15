package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.LoginRequest;
import com.example.demo.requests.RegisterUserRequest;
import com.example.demo.responses.UserResponse;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/session")
@CrossOrigin("*")
public class SessionAuthController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public SessionAuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request, HttpSession session){
        Optional<User> user = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if(user.isPresent()){ // login success
            //set cookie
            session.setAttribute("userId", user.get().getId());
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(HttpSession session){ //input 為 HTTP 的 Header 裡面!! 去解析 Header
        Integer userId = (Integer) session.getAttribute("userId"); //強行轉為 Integer (已確定塞進的為 int)
        if(userId==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<User> user = userRepository.findById(userId);
        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){ //input 為 HTTP 的 Header 裡面!! 去解析 Header
        session.invalidate();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterUserRequest request, HttpSession session){
        // 判斷 username 是否存在
        Optional<User> tempUser = userRepository.findByUsername(request.getUsername());
        if(tempUser.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        User userNew = userRepository.save(user);

        session.setAttribute("userId", user.getId());
        UserResponse response = new UserResponse(userNew.getUsername(), userNew.getEmail());
        return ResponseEntity.ok(response);
    }
}
