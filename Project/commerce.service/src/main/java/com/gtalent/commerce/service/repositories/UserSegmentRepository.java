package com.gtalent.commerce.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtalent.commerce.service.models.Segment;
import com.gtalent.commerce.service.models.UserSegment;

public interface UserSegmentRepository extends JpaRepository<UserSegment, Integer>{

    List<UserSegment> findBySegment(Segment segment);

}
