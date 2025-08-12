package com.example.demo.responses;

import java.math.BigDecimal;

import com.example.demo.models.Product;

public class ProductResponse {
    private String name;
    private BigDecimal price;
    // private Supplier supplier;

    public ProductResponse(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        // this.supplier = supplier;
    }

    public ProductResponse(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public ProductResponse() {
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

    // public Supplier getSupplier() {
    //     return supplier;
    // }

    // public void setSupplier(Supplier supplier) {
    //     this.supplier = supplier;
    // }
}
