package com.gtalent.commerce.service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gtalent.commerce.service.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{
    List<Product> findByIsDeleted(Boolean isDeleted);
    Optional<Product> findByName(String name);

}
