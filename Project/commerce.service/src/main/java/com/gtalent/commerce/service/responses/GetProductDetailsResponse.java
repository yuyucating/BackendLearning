package com.gtalent.commerce.service.responses;

import java.math.BigDecimal;
import com.gtalent.commerce.service.models.Product;

public class GetProductDetailsResponse {
    private String imageUrl;
    private String imageThumbnail;
    private String name;
    private String category;
    private float width;
    private float height;
    private BigDecimal price;
    private int stock;
    private int sales;
    private String description;

    public GetProductDetailsResponse(Product product){
        this.imageUrl = product.getImageUrl();
        this.imageThumbnail = product.getImageThumbnail();
        this.name = product.getName();
        this.category = product.getCategory().getName();
        this.width = product.getWidth();
        this.height = product.getHeight();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.sales = product.getSales();
        this.description = product.getDescription();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getSales() {
        return sales;
    }

    public String getDescription() {
        return description;
    }

}
