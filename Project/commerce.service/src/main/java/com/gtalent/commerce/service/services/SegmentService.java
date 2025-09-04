package com.gtalent.commerce.service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.Segment;
import com.gtalent.commerce.service.repositories.SegmentRepository;


@Service
public class SegmentService {
    private final SegmentRepository segmentRepository;

    @Autowired
    public SegmentService(SegmentRepository segmentRepository){
        this.segmentRepository = segmentRepository;
    }

    public List<Segment> getAllSegments(){
        try{
            List<Segment> segments = segmentRepository.findAll();

            return segments;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
