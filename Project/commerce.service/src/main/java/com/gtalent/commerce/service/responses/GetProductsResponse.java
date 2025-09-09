package com.gtalent.commerce.service.responses;

import com.gtalent.commerce.service.models.Product;

public class GetProductsResponse {
    private String name;
    private String img;
    private String spec;

    public GetProductsResponse() {
    }

    public GetProductsResponse(Product product) {
        this.name = product.getName();
        this.img = product.getImageUrl();
        this.spec = product.getWidth()+"x"+product.getHeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setSpec(String weight, String height) {
        this.spec = weight+"x"+height;
    }
}
