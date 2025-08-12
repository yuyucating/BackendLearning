package com.example.demo.requests;

import com.example.demo.models.Supplier;

public class CreateSupplierRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean is_deleted;
    private Supplier supplier;

    public CreateSupplierRequest() {
    }

    public CreateSupplierRequest(String name, String address, String phone, String email, boolean is_deleted, Supplier supplier) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.is_deleted = is_deleted;
        this.supplier = supplier;
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

    public boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
