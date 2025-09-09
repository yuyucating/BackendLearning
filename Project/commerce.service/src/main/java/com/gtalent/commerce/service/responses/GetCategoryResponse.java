package com.gtalent.commerce.service.responses;

import com.gtalent.commerce.service.models.Category;

public class GetCategoryResponse {
    private String name;

    public GetCategoryResponse() {
    }

    public GetCategoryResponse(String name) {
        this.name = name;
    }

    public GetCategoryResponse(Category category){
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
