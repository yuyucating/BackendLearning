package com.example.demo.requests;

import java.math.BigDecimal;

import com.example.demo.models.Supplier;

public class UpdateProductRequest {
    private  String name;
    private BigDecimal price;
    private Supplier supplier;

    public UpdateProductRequest(String name, BigDecimal price, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.supplier = supplier;
    }
    public UpdateProductRequest() {
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
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
