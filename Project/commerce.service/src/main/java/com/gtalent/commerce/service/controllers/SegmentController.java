package com.gtalent.commerce.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.Segment;
import com.gtalent.commerce.service.responses.SegmentResponse;
import com.gtalent.commerce.service.services.SegmentService;

@RestController
@RequestMapping("v1/segments")
@CrossOrigin("*")
public class SegmentController {
    private final SegmentService segmentService;

    @Autowired
    public SegmentController (SegmentService segmentService){
        this.segmentService = segmentService;
    }

    @GetMapping
    public ResponseEntity<List<SegmentResponse>> getAllSegment(){
        List<Segment> segments = segmentService.getAllSegments();

        return ResponseEntity.ok(segments.stream().map(SegmentResponse::new).toList());
    }

    // @GetMapping("/{id}/segments/{segmentId}")
    // public ResponseEntity<List<GetSegmentUsersResponse>>getSegmentUsers(@PathVariable int id, @PathVariable int segmentId){
    //     // List<User> users = userRepository.assignS
    //     return ResponseEntity.ok().build();
    // }
}
