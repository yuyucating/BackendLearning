package com.gtalent.commerce.service.repositories;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gtalent.commerce.service.models.Segment;
import com.gtalent.commerce.service.models.User;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
    // SELECT * FROM users Where username=???
    // Optional<User> findByUsername(String username); 
    // 根據 segment 的 id 查 User

    List<User> findByIsDeleted(Boolean isDeleted);
    Optional<User> findByEmail(String email);
    List<User> findByUserSegmentsSegmentId(Integer segmentId);

    // 或者根據 segment 的名稱查 User
    // List<User> findByUserSegmentsSegmentName(String segmentName);
    // Page<User> findByNameContainingIgnoreCase(String query, Pageable pageable);
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    List<User> findByLastLoginTimeBetween(LocalDate dateFrom, LocalDate dateTo);

    @Query("SELECT u FROM User u WHERE SIZE(u.orders) > :count")
    List<User> findByOrdersGreaterThan(@Param("count") int orders);

    @Query("SELECT u FROM User u WHERE SIZE(u.orders) = :count")
    List<User> findByOrders(@Param("count") int orders);

    List<User> findByHasNewsletter(boolean hasNewsletter);

    // List<User> findBySegmentsSegmentName(String segmentName);
    @Query("SELECT u FROM User u JOIN u.userSegments us WHERE us.segment = :segment")
    List<User> findByUserSegment(@Param("segment") Segment segment);

}
