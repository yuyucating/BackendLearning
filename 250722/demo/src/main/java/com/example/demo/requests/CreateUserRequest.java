package com.example.demo.requests;

public class CreateUserRequest {
    private String username;
    private String email;

    public CreateUserRequest(String email, String username) {
        this.email = email;
        this.username = username;
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
