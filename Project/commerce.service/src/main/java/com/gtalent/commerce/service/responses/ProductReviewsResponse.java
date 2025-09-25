package com.gtalent.commerce.service.responses;

import java.time.LocalDate;

import com.gtalent.commerce.service.enums.Rating;
import com.gtalent.commerce.service.enums.ReviewStatus;
import com.gtalent.commerce.service.models.Review;

public class ProductReviewsResponse {
    private LocalDate date;
    private String customer;
    private Rating rating;
    private String comment;
    private ReviewStatus status;

    public ProductReviewsResponse(Review review){
        this.date = review.getDate();
        this.customer = review.getUser().getFullName();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.status = review.getStatus();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCustomer() {
        return customer;
    }

    public Rating getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public ReviewStatus getStatus() {
        return status;
    }
}
