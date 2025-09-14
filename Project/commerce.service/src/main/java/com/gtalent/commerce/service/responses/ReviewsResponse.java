package com.gtalent.commerce.service.responses;

import com.gtalent.commerce.service.enums.Rating;
import com.gtalent.commerce.service.enums.ReviewStatus;
import com.gtalent.commerce.service.models.Review;


public class ReviewsResponse {
    private String userName;
    private String productName;
    private Rating rating;
    private String status;
    private String comment;

    public ReviewsResponse() {
    }

    public ReviewsResponse(String comment, String productName, Rating rating, String status, String userName) {
        this.comment = comment;
        this.productName = productName;
        this.rating = rating;
        this.status = status;
        this.userName = userName;
    }

    public ReviewsResponse(Review review) {
        this.userName = review.getUser().getFirstName()+" "+review.getUser().getLastName();
        this.productName = review.getProduct().getName();
        this.rating = review.getRating();
        this.status = review.getStatus().getValue();
        this.comment = review.getComment();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(ReviewStatus status) {
        if(status != null) {
        this.status = status.getValue();
    } else {
        this.status = "Pending";
    }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
