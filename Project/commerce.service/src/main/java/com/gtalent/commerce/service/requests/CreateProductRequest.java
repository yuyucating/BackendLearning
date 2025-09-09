package com.gtalent.commerce.service.requests;

import java.math.BigDecimal;

public class CreateProductRequest {
    private String name;
    private float width;
    private float height;
    private BigDecimal price;
    private int stock = 0;
    private int sales = 0;
    private String imageUrl;
    private String imageThumbnail;
    private String description;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String description, float height, String imageThumbnail, String imageUrl, String name, BigDecimal price, float width) {
        this.description = description;
        this.height = height;
        this.imageThumbnail = imageThumbnail;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
