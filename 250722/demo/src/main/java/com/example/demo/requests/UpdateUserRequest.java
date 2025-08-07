package com.example.demo.requests;

public class UpdateUserRequest {
    // private 
    private String username;

    public UpdateUserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
