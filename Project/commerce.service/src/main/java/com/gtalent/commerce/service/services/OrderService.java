package com.gtalent.commerce.service.services;

import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.responses.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
