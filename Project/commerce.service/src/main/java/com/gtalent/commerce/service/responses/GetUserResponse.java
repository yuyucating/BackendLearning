package com.gtalent.commerce.service.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.gtalent.commerce.service.models.UserSegment;

public class GetUserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private boolean hasNewsletter;
    private List<UserSegment> userSegments;
    private LocalDateTime lastLoginTime;
    private boolean isDeleted;

    public GetUserResponse() {
    }

    public GetUserResponse(String firstName, String lastName, String email, LocalDate birthday, String address,
            String city, String state, String zipcode, boolean hasNewsletter, List<UserSegment> userSegments,
            LocalDateTime lastLoginTime, boolean isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.hasNewsletter = hasNewsletter;
        this.userSegments = userSegments;
        this.lastLoginTime = lastLoginTime;
        this.isDeleted = isDeleted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isHasNewsletter() {
        return hasNewsletter;
    }

    public void setHasNewsletter(boolean hasNewsletter) {
        this.hasNewsletter = hasNewsletter;
    }

    public List<UserSegment> getUserSegments() {
        return userSegments;
    }

    public void setUserSegments(List<UserSegment> userSegments) {
        this.userSegments = userSegments;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    
}
