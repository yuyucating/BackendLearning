package com.gtalent.commerce.service.responses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.models.UserSegment;

public class GetUserListResponse {
    private int id;
    private String name;
    private LocalDateTime lastLoginTime;
    private int orders=0;
    private float totalSpent;
    private LocalDateTime latestPurchase;
    private boolean has_newletter;
    private List<String> segments = new ArrayList<>();
    
    public GetUserListResponse() {
    }

    public GetUserListResponse(int id, String firstName, String lastName, LocalDateTime lastLoginTime, int orders, float totalSpent,
            LocalDateTime latestPurchase, boolean has_newletter, List<UserSegment> segments) {
        this.id = id;
        this.name = firstName+" "+lastName;
        this.lastLoginTime = lastLoginTime;
        this.orders = orders;
        this.totalSpent = totalSpent;
        this.latestPurchase = latestPurchase;
        this.has_newletter = has_newletter;
        this.segments = segments.stream().map(segment->segment.getSegment().getName()).toList();
    }

    public GetUserListResponse(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.lastLoginTime = user.getLastLoginTime();
        this.orders = 0; // TODO: 根據實際資料來源填
        this.totalSpent = 0; // TODO: 同上
        this.latestPurchase = null; // TODO: 同上
        this.has_newletter = user.isHasNewsletter();
        this.segments = user.getUserSegments().stream().map(userSegment->userSegment.getSegment().getName()).toList();
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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
