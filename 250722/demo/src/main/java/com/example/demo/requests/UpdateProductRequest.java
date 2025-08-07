package com.example.demo.requests;

public class UpdateProductRequest {
    private  String name;
    private float price;
    public UpdateProductRequest(String name, float price) {
        this.name = name;
        this.price = price;
    }
    public UpdateProductRequest() {
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
