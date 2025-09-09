package com.gtalent.commerce.service.models;

public enum Rating{
    One(1), Two(2), Three(3), Four(4), Five(5);

    private final int value;

    Rating(int value) { this.value = value; }
    public int getValue() { return value; }
}
