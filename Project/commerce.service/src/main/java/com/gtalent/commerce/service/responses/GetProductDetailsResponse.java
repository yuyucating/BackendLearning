package com.gtalent.commerce.service.responses;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gtalent.commerce.service.models.Product;
import com.gtalent.commerce.service.models.Review;
import com.gtalent.commerce.service.services.ReviewService;

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
    private Page<ProductReviewsResponse> reviews;

    public GetProductDetailsResponse(Product product, PageRequest pageRequest){
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
        this.reviews = getReviews(product, pageRequest);
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

    public Page<ProductReviewsResponse> getReviews() {
        return reviews;
    }

    private Page<ProductReviewsResponse> getReviews(Product product, PageRequest pageRequest){
        ReviewService reviewService = new ReviewService(null, null, null);
        Page<Review> reviews = reviewService.getProductReviews(product, pageRequest);
        if(reviews==null || reviews.isEmpty()){
            return null;
        }else{
            Page<ProductReviewsResponse> response = reviews.map(ProductReviewsResponse::new);
            return response;
        }
    }
}
