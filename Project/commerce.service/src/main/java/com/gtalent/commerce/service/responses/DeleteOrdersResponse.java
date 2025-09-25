package com.gtalent.commerce.service.responses;

import com.gtalent.commerce.service.models.Order;

public class DeleteOrdersResponse {
    private int id;
    private String username;
    private boolean isDeleted;

    public DeleteOrdersResponse(Order order){
        this.id = order.getId();
        this.username = order.getUser().getFullName();
        this.isDeleted = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
