package com.gtalent.commerce.service.responses;

import com.gtalent.commerce.service.models.User;

public class DeleteUserResponse {
    private int id;
    private String username;
    private boolean isDeleted;

    public DeleteUserResponse(){

    }
    public DeleteUserResponse(User user){
        this.id = user.getId();
        this.username = user.getFirstName()+" "+user.getLastName();
        this.isDeleted = user.isDeleted();
    }

    public int getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public boolean IsDeleted(){
        return this.isDeleted;
    }
}
