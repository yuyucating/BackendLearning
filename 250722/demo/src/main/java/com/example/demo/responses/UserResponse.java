package com.example.demo.responses;
import com.example.demo.models.User;

public class UserResponse {
    private String username;
    private String email;
    private String role;
    private User user;
    
    public UserResponse() {
    }
    public UserResponse(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }
    public UserResponse(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
