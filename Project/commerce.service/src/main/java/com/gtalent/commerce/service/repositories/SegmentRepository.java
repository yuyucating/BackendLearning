package com.gtalent.commerce.service.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtalent.commerce.service.models.Segment;

public interface SegmentRepository extends JpaRepository<Segment, Integer>{

    List<Segment> findByName(String name);


}
