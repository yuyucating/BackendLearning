package com.gtalent.commerce.service.requests;

import java.util.List;

import com.gtalent.commerce.service.enums.ReviewStatus;

public class UpdateReviewStatusRequest {
    private List<Integer> ids;
    private ReviewStatus status;

    public UpdateReviewStatusRequest(List<Integer> ids, ReviewStatus status) {
        this.ids = ids;
        this.status = status;
    }
    public UpdateReviewStatusRequest() {
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }
}
