package com.gtalent.commerce.service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtalent.commerce.service.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

    Optional<Category> findByName(String name);
    List<Category> findByIsDeleted(Boolean isDeleted);

}
