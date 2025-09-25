package com.gtalent.commerce.service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.requests.CreateOrderRequest;
import com.gtalent.commerce.service.requests.IdListRequest;
import com.gtalent.commerce.service.responses.CreateOrderResponse;
import com.gtalent.commerce.service.responses.DeleteOrdersResponse;
import com.gtalent.commerce.service.responses.GetAllOrdersResponse;
import com.gtalent.commerce.service.responses.GetOrderResponse;
import com.gtalent.commerce.service.responses.OrderUpdateResponse;
import com.gtalent.commerce.service.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/orders")
@CrossOrigin("*")
@Tag(name="Order Controller")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request){
        Order order = orderService.createOrder(request);
        if(order!=null){
            CreateOrderResponse response = new CreateOrderResponse(order);
            System.out.println("★☆★☆"+response.getDatetime());
            return ResponseEntity.ok(response);
        }return ResponseEntity.noContent().build();
    }

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

    @Operation(summary="Get order information",
    description="This API returns order with specified order ids.")
    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable int id){
        Order order = orderService.getOrder(id);
        if(order!=null){
            GetOrderResponse response = new GetOrderResponse(order);
            return ResponseEntity.ok(response);
        }return ResponseEntity.notFound().build();
    }

    @Operation(summary="Update order status",
    description="This API updates order with specified order ids.")
    @PutMapping
    public ResponseEntity<OrderUpdateResponse> updateOrderStatus(@RequestBody int id,
    @Parameter(
        description="Update order status",
        schema=@Schema(allowableValues= {"ordered", "delivered", "cancelled"})
    )@RequestParam(required=true) String status
    ){
        Order orders = orderService.updateOrderStatus(id, status);
        if(orders!=null){
            return ResponseEntity.ok(new OrderUpdateResponse(orders));
        }return ResponseEntity.noContent().build();
    }

    @Operation(summary="Delete order(s)",
    description="This API deletes order(s) with specified order ids.")
    @PutMapping("/delete")
    public ResponseEntity<List<DeleteOrdersResponse>> deleteUsers(@RequestBody IdListRequest request){
        List<Order> orders = orderService.deleteOrders(request);
        if(orders!=null && !orders.isEmpty()){
            return ResponseEntity.ok(orders.stream().map(DeleteOrdersResponse::new).toList());
        }return ResponseEntity.noContent().build();
    }
}
