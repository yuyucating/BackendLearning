package com.gtalent.commerce.service.responses;

import com.gtalent.commerce.service.enums.OrderStatus;
import com.gtalent.commerce.service.models.Order;

public class OrderUpdateResponse {
    private int id;
    private String username;
    private OrderStatus status;

    public OrderUpdateResponse(Order order){
        this.id = order.getId();
        this.username = order.getUser().getFullName();
        this.status = order.getStatus();
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
