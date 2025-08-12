package com.example.demo.requests;

import com.example.demo.models.User;

public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
    private User user;

    public RegisterUserRequest() {
    }
    public RegisterUserRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public RegisterUserRequest(User user){
        this.user = user;
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
