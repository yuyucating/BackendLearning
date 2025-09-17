package com.gtalent.commerce.service.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.models.OrderProduct;

public class GetAllOrdersResponse {
    private LocalDateTime datetime;
    private int orderId;
    private String username;
    private String address;
    private int productkinds;
    private BigDecimal totals;

    public GetAllOrdersResponse() {
    }

    public GetAllOrdersResponse(LocalDateTime datetime, int orderId, String username, String address, int productkinds,
            BigDecimal totals) {
        this.datetime = datetime;
        this.orderId = orderId;
        this.username = username;
        this.address = address;
        this.productkinds = productkinds;
        this.totals = totals;
    }

    public GetAllOrdersResponse(Order order) {
        this.datetime = order.getDatetime();
        this.orderId = order.getId();
        this.username = order.getUser().getFirstName()+" "+order.getUser().getLastName();
        this.address = order.getUser().getAddress();
        this.productkinds = order.getOrderProducts().size();
        this.totals = order.getTotals();
    }

    // public String toString(){
    //     return (
    //         "★★★★\norderId = "+this.orderId+"\nusername = "+this.username+"\ntotals = "+this.totals+"\n★★★"
    //     );
    // }
    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProductkinds() {
        return productkinds;
    }

    public void setProductkinds(int productkinds) {
        this.productkinds = productkinds;
    }

    public BigDecimal getTotals() {
        return totals;
    }

    public void setTotals(BigDecimal totals) {
        this.totals = totals;
    }
}
