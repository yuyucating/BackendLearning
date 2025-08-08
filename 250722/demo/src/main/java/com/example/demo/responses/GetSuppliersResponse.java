package com.example.demo.responses;

import com.example.demo.models.Supplier;

public class GetSuppliersResponse {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean is_deleted;
    private Supplier supplier;

    public GetSuppliersResponse() {
    }

    public GetSuppliersResponse(Supplier supplier){
        this.supplier = supplier;
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.address = supplier.getAddress();
        this.phone = supplier.getPhone();
        this.email = supplier.getEmail();
        this.is_deleted = supplier.getIs_deleted();
    }

    public GetSuppliersResponse(String address, String email, int id, String name, String phone, boolean is_deleted) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.is_deleted = is_deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
