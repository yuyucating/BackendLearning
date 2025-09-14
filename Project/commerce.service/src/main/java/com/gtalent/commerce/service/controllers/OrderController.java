package com.gtalent.commerce.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.requests.CreateOrderRequest;
import com.gtalent.commerce.service.services.OrderService;

@RestController
@RequestMapping("v1/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderservice){
        this.orderService = orderService;
    }

    //??
    public ResponseEntity<createOrderResponse> createOrder(@RequestBody CreateOrderRequest request){
        
    }

    //getAllOrdersPage
        //Ordered
        //Delivered
        //Cancelled

    

    //getOrder
    

    //updateOrder

    //MultiDelete


}
