package com.example.demo.requests;

public class UpdateSupplierRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean is_deleted;

    public UpdateSupplierRequest() {
    }

    public UpdateSupplierRequest(String address, String email, boolean is_deleted, String name, String phone) {
        this.address = address;
        this.email = email;
        this.is_deleted = is_deleted;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
