package com.gtalent.commerce.service.repositories;
import java.util.List;

import com.gtalent.commerce.service.models.OrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtalent.commerce.service.models.Order;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer>{
    List<OrderProduct> findByOrder(Order order);
}
