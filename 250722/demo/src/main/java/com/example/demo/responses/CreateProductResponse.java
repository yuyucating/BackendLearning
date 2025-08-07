package com.example.demo.responses;

public class CreateProductResponse {
    private  String name;
    private float price;
    public CreateProductResponse(String name, float price) {
        this.name = name;
        this.price = price;
    }
    public CreateProductResponse() {
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    
}
