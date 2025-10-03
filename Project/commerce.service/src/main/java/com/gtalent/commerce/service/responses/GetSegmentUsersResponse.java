package com.gtalent.commerce.service.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gtalent.commerce.service.models.Segment;
import com.gtalent.commerce.service.models.User;

public class GetSegmentUsersResponse {
    private String user_name;
    private LocalDateTime user_lastSeen;
    private int user_orders;
    private float user_totalSpent;
    private LocalDateTime user_latestPurchase;
    private boolean hasNewsLetter;
    private List<String> segments = new ArrayList<>();

    public GetSegmentUsersResponse() {
    }

    public GetSegmentUsersResponse(boolean hasNewsLetter, List<String> segments, LocalDateTime user_lastSeen, LocalDateTime user_latestPurchase, String user_name, int user_orders, float user_totalSpent) {
        this.hasNewsLetter = hasNewsLetter;
        this.segments = segments;
        this.user_lastSeen = user_lastSeen;
        this.user_latestPurchase = user_latestPurchase;
        this.user_name = user_name;
        this.user_orders = user_orders;
        this.user_totalSpent = user_totalSpent;
    }

    public GetSegmentUsersResponse(User user) {
        this.hasNewsLetter = user.isHasNewsletter();
        this.segments = user.getUserSegments().stream().map(userSegment->userSegment.getSegment().getName()).toList();
        this.user_lastSeen = user.getLastLoginTime();
        this.user_latestPurchase = null; // TODO: 根據實際資料來源填
        this.user_name = user.getFirstName() + " " + user.getLastName();
        this.user_orders = 0; // TODO: 根據實際資料來源填
        this.user_totalSpent = 0; // TODO: 根據實際資料來源填

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public LocalDateTime getUser_lastSeen() {
        return user_lastSeen;
    }

    public void setUser_lastSeen(LocalDateTime user_lastSeen) {
        this.user_lastSeen = user_lastSeen;
    }

    public int getUser_orders() {
        return user_orders;
    }

    public void setUser_orders(int user_orders) {
        this.user_orders = user_orders;
    }

    public float getUser_totalSpent() {
        return user_totalSpent;
    }

    public void setUser_totalSpent(float user_totalSpent) {
        this.user_totalSpent = user_totalSpent;
    }

    public LocalDateTime getUser_latestPurchase() {
        return user_latestPurchase;
    }

    public void setUser_latestPurchase(LocalDateTime user_latestPurchase) {
        this.user_latestPurchase = user_latestPurchase;
    }

    public boolean isHasNewsLetter() {
        return hasNewsLetter;
    }

    public void setHasNewsLetter(boolean hasNewsLetter) {
        this.hasNewsLetter = hasNewsLetter;
    }



}
