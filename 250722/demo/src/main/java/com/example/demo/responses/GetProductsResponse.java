package com.example.demo.responses;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.models.Product;
import com.example.demo.models.Supplier;

public class GetProductsResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private int status;
    private Map<Integer, String> supplier= new HashMap<>();

    public GetProductsResponse(){
        
    }

    public GetProductsResponse(int id, String name, BigDecimal price, int quantity, int status, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status; 
        if (supplier != null) { // ✅ 避免 NPE
            this.supplier.put(supplier.getId(), supplier.getName());
        }else{
            this.supplier = null;
        }
    }

    public GetProductsResponse(Product product){
        // this.product = product;
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.status = product.getStatus();
        Supplier supplier = product.getSupplier();
        if (supplier != null) { // ✅ 避免 NPE
            this.supplier.put(supplier.getId(), supplier.getName());
        }else{
            this.supplier = null;
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, String> getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        if (supplier != null) { // ✅ 避免 NPE
            this.supplier.put(supplier.getId(), supplier.getName());
        }else{
            this.supplier = null;
        }
    }
    
}
