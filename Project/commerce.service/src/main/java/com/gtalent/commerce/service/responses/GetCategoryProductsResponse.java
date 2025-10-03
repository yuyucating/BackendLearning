package com.gtalent.commerce.service.responses;

import java.util.ArrayList;
import java.util.List;

import com.gtalent.commerce.service.models.Category;
import com.gtalent.commerce.service.models.Product;

public class GetCategoryProductsResponse {
    private String name;
    private List<Product> products = new ArrayList<>(); //todo: show product information?

    public GetCategoryProductsResponse() {
    }

    public GetCategoryProductsResponse(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public GetCategoryProductsResponse(Category category) {
        this.name = category.getName();
        this.products = category.getProducts();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProduct() {
        return products;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }

}
