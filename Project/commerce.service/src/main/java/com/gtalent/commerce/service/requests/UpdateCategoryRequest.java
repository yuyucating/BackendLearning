package com.gtalent.commerce.service.requests;

import java.util.List;

import com.gtalent.commerce.service.models.Category;
import com.gtalent.commerce.service.models.Product;


//todo: 好像不應該是放入 Product 當參數，Swagger 甚至可以修改該 Product 的內容?
public class UpdateCategoryRequest {
    private String name;
    private List<Product> products;
    private boolean isDelete;

    public UpdateCategoryRequest() {
    }

    public UpdateCategoryRequest(boolean isDelete, String name, List<Product> products) {
        this.isDelete = isDelete;
        this.name = name;
        this.products = products;
    }

    public UpdateCategoryRequest(Category category) {
        this.name = category.getName();
        this.products = category.getProducts();
        this.isDelete = category.isDeleted();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}
