package com.example.demo.responses;

import java.math.BigDecimal;

public class ProductResponse {
    private String name;
    private BigDecimal price;

    public ProductResponse(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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
}
