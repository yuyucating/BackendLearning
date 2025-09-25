package com.gtalent.commerce.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.Segment;
import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.responses.GetSegmentUsersResponse;
import com.gtalent.commerce.service.responses.SegmentResponse;
import com.gtalent.commerce.service.services.SegmentService;
import com.gtalent.commerce.service.services.UserSegmentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/segments")
@CrossOrigin("*")
@Tag(name="Segment Controller")
public class SegmentController {
    private final SegmentService segmentService;
    private final UserSegmentService userSegmentService;

    @Autowired
    public SegmentController (SegmentService segmentService, UserSegmentService userSegmentService){
        this.segmentService = segmentService;
        this.userSegmentService = userSegmentService;
    }

    @GetMapping
    public ResponseEntity<List<SegmentResponse>> getAllSegment(){
        List<Segment> segments = segmentService.getAllSegments();

        return ResponseEntity.ok(segments.stream().map(SegmentResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GetSegmentUsersResponse>>getSegmentUsers(@PathVariable int id){
        List<User> users = userSegmentService.getSegmentUsers(id);
        if (users.size()>0){
            return ResponseEntity.ok(users.stream().map(GetSegmentUsersResponse::new).toList());
        }else{
            return ResponseEntity.noContent().build();
        }
        
    }
}
