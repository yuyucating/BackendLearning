package com.gtalent.commerce.service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.gtalent.commerce.service.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>, JpaSpecificationExecutor<Review>{
    
    Optional<Review> findById(int id);
    List<Review> findByCommentContainingIgnoreCase(String query);

}
