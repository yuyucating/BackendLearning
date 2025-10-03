package com.gtalent.commerce.service.requests;

public class CreateReviewRequest {
    private int userId;
    private int productId;
    // private Rating rating;
    private String comment;

    public CreateReviewRequest() {
    }

    public CreateReviewRequest(int userId, String comment, int productId) {
        this.comment = comment;
        this.productId = productId;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
