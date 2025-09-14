package com.gtalent.commerce.service.requests;

import java.time.LocalDate;

import com.gtalent.commerce.service.enums.Rating;

public class CreateReviewRequest {
    private String userFirstName;
    private String userLastName;
    private String product;
    // private Rating rating;
    private String comment;

    public CreateReviewRequest() {
    }

    public CreateReviewRequest(String comment, String product, String userFirstName, String userLastName) {
        this.comment = comment;
        this.product = product;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
