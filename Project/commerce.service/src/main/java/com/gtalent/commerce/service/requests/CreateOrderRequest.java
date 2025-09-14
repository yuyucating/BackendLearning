package com.gtalent.commerce.service.requests;

import java.util.HashMap;
import java.util.Map;

import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.models.OrderProduct;

public class CreateOrderRequest {
    private int userId;
    private Map<Integer, Integer> productQuantity;
    
    public CreateOrderRequest(int userId, Map<Integer, Integer> productQuantity) {
        this.userId = userId;
        this.productQuantity = productQuantity;
    }
    
    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Order order) {
        this.userId = order.getUser().getId();

        Map<Integer, Integer> productQuantity = new HashMap<>();
        for(OrderProduct i:order.getOrderProducts())
            productQuantity.put(i.getProduct().getId(), i.getQty());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Map<Integer, Integer> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Map<Integer, Integer> productQuantity) {
        this.productQuantity = productQuantity;
    }
}
