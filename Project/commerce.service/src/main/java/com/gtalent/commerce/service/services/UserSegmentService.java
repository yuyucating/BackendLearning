package com.gtalent.commerce.service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.Segment;
import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.models.UserSegment;
import com.gtalent.commerce.service.repositories.SegmentRepository;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.responses.UserSegmentRepository;

@Service
public class UserSegmentService {

    private final SegmentRepository segmentRepository;
    private final UserRepository userRepository;
    private final UserSegmentRepository userSegmentRepository;

    @Autowired
    public UserSegmentService(SegmentRepository segmentRepository, UserRepository userRepository, UserSegmentRepository userSegmentRepository){
        this.segmentRepository = segmentRepository;
        this.userRepository = userRepository;
        this.userSegmentRepository = userSegmentRepository;
    }

    public void assignUserSegment(int id, int segmentId){
        Optional<Segment> segment = segmentRepository.findById(segmentId);
        Optional<User> user = userRepository.findById(id);
        
        if (user.isPresent() && segment.isPresent()){
            UserSegment userSegment = new UserSegment();
            userSegment.setUser(user.get());
            userSegment.setSegment(segment.get());

            userSegmentRepository.save(userSegment);
        }

    }

    public List<User> getSegmentUsers(int id){
        try{
            List<User> users = userRepository.findByUserSegmentsSegmentId(id);
            return users;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
