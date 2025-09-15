package com.gtalent.commerce.service.responses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.models.OrderProduct;
import com.gtalent.commerce.service.models.Product;
import com.gtalent.commerce.service.models.User;

public class CreateOrderResponse {
    private int userId;
    private LocalDate date;
    private Map<String, Integer> productQty = new HashMap<>();
    private BigDecimal deliveryFee=BigDecimal.ZERO;
    private BigDecimal tax=BigDecimal.ZERO;
    private BigDecimal totals=BigDecimal.ZERO;
    private String status;

    public CreateOrderResponse(){

    }

    public CreateOrderResponse(Order order){
        this.userId = order.getUser().getId();
        this.date = order.getDate();
        
        this.totals = BigDecimal.ZERO;
        for(OrderProduct product: order.getOrderProducts()){
            this.productQty.put(product.getProduct().getName(), product.getQty());
            BigDecimal qty = new BigDecimal(product.getQty());
            this.totals = this.totals.add(product.getProduct().getPrice().multiply(qty));
        }

        this.deliveryFee = order.getDeliveryFee();
        this.tax = order.getTax();
        this.totals = this.totals.add(this.deliveryFee).add(this.tax);
        this.status = order.getStatus();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Integer> getProductQty() {
        return productQty;
    }

    public void setProductQty(Map<String, Integer> productQty) {
        this.productQty = productQty;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotals() {
        return totals;
    }

    public void setTotals(BigDecimal totals) {
        this.totals = totals;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
