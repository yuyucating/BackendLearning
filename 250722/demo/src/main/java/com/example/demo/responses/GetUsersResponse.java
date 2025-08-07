package com.example.demo.responses;
import com.example.demo.models.User;

public class GetUsersResponse {
    private int id;
    private String username;
    private User user;

    public GetUsersResponse(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public GetUsersResponse(){

    }

    public GetUsersResponse(User user){
        this.user = user;
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
