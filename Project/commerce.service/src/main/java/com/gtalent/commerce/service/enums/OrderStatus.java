package com.gtalent.commerce.service.enums;

public enum OrderStatus {
    ORDERED("order"), DELIVERED("delivered"), CANCELLED("cancelled");

    private String status;

    OrderStatus(String status) { this.status = status; }
    public String getStatus() { return status; }

}
