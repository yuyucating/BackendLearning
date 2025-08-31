package com.gtalent.commerce.service.responses;

import java.time.LocalDateTime;
import java.util.List;

public class GetUserListResponse {
    private String name;
    private LocalDateTime lastLoginTime;
    private int orders=0;
    private float totalSpent;
    private LocalDateTime latestPurchase;
    private boolean has_newletter;
    private List<String> segments;
    
    public GetUserListResponse() {
    }

    public GetUserListResponse(String firstName, String lastName, LocalDateTime lastLoginTime, int orders, float totalSpent,
            LocalDateTime latestPurchase, boolean has_newletter, List<String> segments) {
        this.name = firstName+" "+lastName;
        this.lastLoginTime = lastLoginTime;
        this.orders = orders;
        this.totalSpent = totalSpent;
        this.latestPurchase = latestPurchase;
        this.has_newletter = has_newletter;
        this.segments = segments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public float getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(float totalSpent) {
        this.totalSpent = totalSpent;
    }

    public LocalDateTime getLatestPurchase() {
        return latestPurchase;
    }

    public void setLatestPurchase(LocalDateTime latestPurchase) {
        this.latestPurchase = latestPurchase;
    }

    public boolean isHas_newletter() {
        return has_newletter;
    }

    public void setHas_newletter(boolean has_newletter) {
        this.has_newletter = has_newletter;
    }

    public List<String> getSegments() {
        return segments;
    }

    public void setSegments(List<String> segments) {
        this.segments = segments;
    }
   

}
