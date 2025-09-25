package com.gtalent.commerce.service.responses;

import java.math.BigDecimal;

import com.gtalent.commerce.service.models.OrderProduct;

public class OrderProductsResponse {
    private String productname;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal totals;

    public OrderProductsResponse(OrderProduct op){
        this.productname = op.getProduct().getName();
        this.unitPrice = op.getProduct().getPrice();
        this.quantity = op.getQty();
        this.totals = this.unitPrice.multiply(new BigDecimal(this.quantity));
    }

    public String getProductname() {
        return productname;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotals() {
        return totals;
    }
}
