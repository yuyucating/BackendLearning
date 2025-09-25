package com.gtalent.commerce.service.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.Order;
import com.gtalent.commerce.service.models.OrderProduct;
import com.gtalent.commerce.service.models.Product;
import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.OrderProductRepository;
import com.gtalent.commerce.service.repositories.OrderRepository;
import com.gtalent.commerce.service.repositories.ProductRepository;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.CreateOrderRequest;
import com.gtalent.commerce.service.responses.GetAllOrdersResponse;

import jakarta.persistence.criteria.Predicate;

import org.springframework.transaction.annotation.Transactional;

import com.gtalent.commerce.service.enums.OrderStatus;
import com.gtalent.commerce.service.requests.IdListRequest;

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
            // Set<Product> keys = pQ.keySet();
            for (Map.Entry<Product, Integer> product:pQ.entrySet()) {
                totals = totals.add(product.getKey().getPrice()).multiply(new BigDecimal(product.getValue()));
            }
            
            result.setTax(totals.add(order.getDeliveryFee()).multiply(new BigDecimal("0.05")));
            orderRepository.save(order);

            return result;

        }else{return null;}

    }

    public Order getOrder(int id){
        Optional<Order> order = orderRepository.findById(id);
        if(order!=null && !order.isEmpty()){
            Order result = order.get();
            return result;
        }return null;
    }

    @Transactional(readOnly = true)
    public Page<GetAllOrdersResponse> getAllOrders(String kind, PageRequest pageRequest){
        Specification<Order> spec = orderSpecification(kind);

        Page<Order> orders = orderRepository.findAll(spec, pageRequest);

        return orders.map(order->new GetAllOrdersResponse(order));
    }

    private Specification<Order> orderSpecification(String kind){
        return ((root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            if(kind!=null && !kind.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("status"), kind));
            }

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
        });
    }

    public Order updateOrderStatus(int id, String status){
        Optional<Order> order = orderRepository.findById(id);
        if(order!=null && !order.isEmpty()){
            Order result = order.get();
            result.setStatus(OrderStatus.valueOf(status));
            orderRepository.save(result);
            return result;
        }else{return null;}
    }

    public List<Order> deleteOrders(IdListRequest request){
        List<Order> orders = orderRepository.findAllById(request.getIds());
        if(orders!=null && !orders.isEmpty()){
            orderRepository.deleteAllById(request.getIds());
            return orders;
        }
        return null;
    }
}
