package com.gtalent.commerce.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.requests.CreateOrderRequest;
import com.gtalent.commerce.service.responses.CreateOrderResponse;
import com.gtalent.commerce.service.responses.GetAllOrdersResponse;
import com.gtalent.commerce.service.services.OrderService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

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
            System.out.println("★☆★☆"+response.getDatetime());
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.noContent().build();
        }

    }

    //getAllOrdersPage
    @GetMapping
    public ResponseEntity<Page<GetAllOrdersResponse>> GetAllOrders(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size,
        @Parameter(
            description="status",
            schema=@Schema(allowableValues = {"Ordered", "Delivered", "Cancelled"})
        )
        @RequestParam(required=true) String kind
    ){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<GetAllOrdersResponse> orders = orderService.getAllOrders(kind, pageRequest);
        if(orders==null || orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    //getOrder
    

    //updateOrder

    //MultiDelete


}
