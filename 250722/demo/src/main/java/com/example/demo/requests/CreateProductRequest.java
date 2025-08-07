package com.example.demo.requests;

import java.math.BigDecimal;

public class CreateProductRequest {
    private  String name;
    private BigDecimal price;
    private int quantity;
    private int status;
    private boolean is_deleted;
    private int supplierId;

    public CreateProductRequest(String name, BigDecimal price, int quantity, int status, boolean is_deleted, int supplierId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.is_deleted = is_deleted;
        this.supplierId = supplierId;
    }    
    public CreateProductRequest() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public boolean getIs_deleted() {
        return is_deleted;
    }
    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

}
