package com.gtalent.commerce.service.responses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gtalent.commerce.service.enums.OrderStatus;
import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.models.OrderProduct;
import com.gtalent.commerce.service.repositories.OrderProductRepository;

public class GetOrderResponse {
    private LocalDate date;
    private int id;
    private String customerName;
    private String customerEmail;
    private OrderStatus status;
    private boolean isReturned;
    private String address;
    private List<OrderProductsResponse> items = new ArrayList<>();
    private BigDecimal sum;
    private BigDecimal delivery;
    private BigDecimal tax;
    private BigDecimal totals;
    
    public GetOrderResponse(){}
    public GetOrderResponse(Order order){
        this.date = order.getDatetime().toLocalDate();
        this.id = order.getId();
        this.customerName = order.getUser().getFullName();
        this.customerEmail = order.getUser().getEmail();
        this.status = order.getStatus();
        this.isReturned = order.isReturned();
        this.address = order.getUser().getAddress();
        this.items = getOrderProducts(order);
        this.sum = order.getSum();
        this.tax = order.getTax();
        this.totals = order.getTotals();
    }

    private List<OrderProductsResponse> getOrderProducts(Order order){
        List<OrderProduct> ops = order.getOrderProducts();
        return ops.stream().map(OrderProductsResponse::new).toList();
    }

    public LocalDate getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean isIsReturned() {
        return isReturned;
    }

    public String getAddress() {
        return address;
    }

    public List<OrderProductsResponse> getItems() {
        return items;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public BigDecimal getDelivery() {
        return delivery;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotals() {
        return totals;
    }
}
