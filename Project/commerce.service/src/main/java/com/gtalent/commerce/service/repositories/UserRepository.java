package com.gtalent.commerce.service.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gtalent.commerce.service.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    // SELECT * FROM users Where username=???
    // Optional<User> findByUsername(String username); 
    // 根據 segment 的 id 查 User
    List<User> findByUserSegmentsSegmentId(Integer segmentId);

    // 或者根據 segment 的名稱查 User
    // List<User> findByUserSegmentsSegmentName(String segmentName);
    // Page<User> findByNameContainingIgnoreCase(String query, Pageable pageable);
    Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName, PageRequest pageRequest);
}
