package com.gtalent.commerce.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtalent.commerce.service.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findByIsDeleted(Boolean isDeleted);

}
