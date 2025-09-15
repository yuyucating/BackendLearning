package com.gtalent.commerce.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.requests.CreateOrderRequest;
import com.gtalent.commerce.service.responses.CreateOrderResponse;
import com.gtalent.commerce.service.services.OrderService;

@RestController
@RequestMapping("v1/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    //??
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request){
        Order order = orderService.createOrder(request);
        if(order!=null){
            CreateOrderResponse response = new CreateOrderResponse(order);
            return ResponseEntity.ok().body(response);
        }else{
            return ResponseEntity.noContent().build();
        }

    }

    //getAllOrdersPage
        //Ordered
        //Delivered
        //Cancelled

    

    //getOrder
    

    //updateOrder

    //MultiDelete


}
