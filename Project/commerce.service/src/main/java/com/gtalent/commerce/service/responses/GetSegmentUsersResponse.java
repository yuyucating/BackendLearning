package com.gtalent.commerce.service.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.gtalent.commerce.service.models.Segment;

public class GetSegmentUsersResponse {
    private String user_name;
    private LocalDate user_lastSeen;
    private int user_orders;
    private float user_totalSpent;
    private LocalDateTime user_latestPurchase;
    private boolean hasNewsLetter;
    private List<Segment> segments;

    public GetSegmentUsersResponse() {
    }

    public GetSegmentUsersResponse(boolean hasNewsLetter, List<Segment> segments, LocalDate user_lastSeen, LocalDateTime user_latestPurchase, String user_name, int user_orders, float user_totalSpent) {
        this.hasNewsLetter = hasNewsLetter;
        this.segments = segments;
        this.user_lastSeen = user_lastSeen;
        this.user_latestPurchase = user_latestPurchase;
        this.user_name = user_name;
        this.user_orders = user_orders;
        this.user_totalSpent = user_totalSpent;
    }

    public GetSegmentUsersResponse(Segment segment) {
        //TODO

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public LocalDate getUser_lastSeen() {
        return user_lastSeen;
    }

    public void setUser_lastSeen(LocalDate user_lastSeen) {
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

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }


}
