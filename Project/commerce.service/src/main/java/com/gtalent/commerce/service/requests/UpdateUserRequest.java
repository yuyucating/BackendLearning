package com.gtalent.commerce.service.requests;

import java.time.LocalDate;

public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String address = "";
    private String city = "";
    private String state = "";
    private String zipcode = "";
    private String password;
    private boolean hasNewsletter = true;
    private boolean isDeleted = false;

    public UpdateUserRequest() {
    }
    public UpdateUserRequest(String firstName, String lastName, String email, LocalDate birthday, String address,
            String city, String state, String zipcode, String password, boolean hasNewsletter, boolean isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.password = password;
        this.hasNewsletter = hasNewsletter;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isHasNewsletter() {
        return hasNewsletter;
    }
    public void setHasNewsletter(boolean hasNewsletter) {
        this.hasNewsletter = hasNewsletter;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
