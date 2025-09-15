package com.gtalent.commerce.service.repositories;

import com.gtalent.commerce.service.models.OrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer>{

}
