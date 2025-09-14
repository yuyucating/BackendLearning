package com.gtalent.commerce.service.enums;

public enum ReviewStatus {
    Pending("Pending"), Accepted("Accepted"), Rejected("Rejected");

    private final String value;

    ReviewStatus(String value) { this.value = value; }
    public String getValue() { return value; }
}
