package com.gtalent.commerce.service.responses;

import java.math.BigDecimal;

import com.gtalent.commerce.service.models.Category;
import com.gtalent.commerce.service.models.Product;

public class ProductDetailsResponse {
    private String name;
    private String categoryName;
    private float width;
    private float height;
    private BigDecimal price;
    private int stock = 0;
    private int sales = 0;

    public ProductDetailsResponse() {
    }

    public ProductDetailsResponse(String name, Category category, float width, float height, BigDecimal price,
            int stock, int sales) {
        this.name = name;
        this.categoryName = category.getName();
        this.width = width;
        this.height = height;
        this.price = price;
        this.stock = stock;
        this.sales = sales;
    }

    public ProductDetailsResponse(Product product) {
        this.name = product.getName();
        this.categoryName = product.getCategory().getName();
        this.width = product.getWidth();
        this.height = product.getHeight();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.sales = product.getSales();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getSales() {
        return sales;
    }
    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category category) {
        this.categoryName = category.getName();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
