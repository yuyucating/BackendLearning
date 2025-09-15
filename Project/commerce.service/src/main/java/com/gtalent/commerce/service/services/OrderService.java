package com.gtalent.commerce.service.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.models.OrderProduct;
import com.gtalent.commerce.service.models.Product;
import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.OrderProductRepository;
import com.gtalent.commerce.service.repositories.ProductRepository;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.CreateOrderRequest;
import com.gtalent.commerce.service.responses.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public Order createOrder(CreateOrderRequest request){
        //先建立一個訂單
        Order order = new Order();
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user!=null && !user.isEmpty()){
            order.setUser(user.get());
        }else{return null;}

        order.setDeliveryFee(BigDecimal.ZERO); //TODO 依實際需求填寫
        int orderId = orderRepository.save(order).getId();

        //建立訂單的產品資訊
        Optional<Order> createdOrder = orderRepository.findById(orderId);
        if(createdOrder.isPresent()){
            Order result = createdOrder.get();
            Set<Integer> productKeys = request.getProductQuantity().keySet();
            for(int i:productKeys){
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(result);

                Optional<Product> product = productRepository.findById(i);
                if(product.isPresent()){
                    orderProduct.setProduct(productRepository.findById(i).get());
                }else{return null;}
                
                orderProduct.setQty(request.getProductQuantity().get(i));
                orderProductRepository.save(orderProduct);
            }

            //將產品資訊統計到訂單
            // Map<Product, Integer> pQ = new HashMap<>();
            // for(OrderProduct op:result.getOrderProducts()){
            //     pQ.put(op.getProduct(), op.getQty());
            // }

            // 建立一個 list 暫存剛建立的 OrderProduct
            List<OrderProduct> orderProducts = new ArrayList<>();

            for(int i: productKeys){
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(result);

                Optional<Product> product = productRepository.findById(i);
                if(product.isPresent()){
                    orderProduct.setProduct(product.get());
                }else{return null;}
                
                orderProduct.setQty(request.getProductQuantity().get(i));
                orderProductRepository.save(orderProduct);

                // 把 OrderProduct 加入 list
                orderProducts.add(orderProduct);
                result.getOrderProducts().add(orderProduct);
            }

            // 計算 totals
            Map<Product, Integer> pQ = new HashMap<>();
            for(OrderProduct op: orderProducts){ // 用剛剛的 list
                pQ.put(op.getProduct(), op.getQty());
            }


            BigDecimal totals= BigDecimal.ZERO;
            Set<Product> keys = pQ.keySet();
            for (Product product:keys) {
                totals = totals.add(product.getPrice());
            }
            
            result.setTax(totals.add(order.getDeliveryFee()).multiply(new BigDecimal("0.05")));
            orderRepository.save(order);

            return result;

        }else{return null;}

    }
}
