package com.example.demo.responses;

import java.math.BigDecimal;

import com.example.demo.models.Product;

public class GetProductsResponse {
    private String name;
    private BigDecimal price;
    private int quantity;
    private int status;
    private Product product;

    public GetProductsResponse(){
        
    }
    public GetProductsResponse(String name, BigDecimal price, int quantity, int status) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public GetProductsResponse(Product product){
        this.product = product;
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.status = product.getStatus();
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
    
}
