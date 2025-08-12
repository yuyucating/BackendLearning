package com.example.demo.responses;
import com.example.demo.models.User;

public class UserResponse {
    private String username;
    private String email;
    private User user;
    
    public UserResponse() {
    }
    public UserResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public UserResponse(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.email = user.getEmail();
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
    
}
