package com.gtalent.commerce.service.services;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.Segment;
import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.SegmentRepository;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.repositories.UserSegmentRepository;
import com.gtalent.commerce.service.requests.UpdateUserRequest;
import com.gtalent.commerce.service.responses.GetUserResponse;

import jakarta.persistence.criteria.Predicate;


// 邏輯判斷在這裡
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SegmentRepository segmentRepository;
    private final UserSegmentRepository userSegmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, SegmentRepository segmentRepository, UserSegmentRepository userSegmentRepository){
        this.userRepository = userRepository;
        this.segmentRepository = segmentRepository;
        this.userSegmentRepository = userSegmentRepository;
    }

    public List<User> getAllUsers(){
        try{
            List<User> users = userRepository.findAll();
            return users;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }

    public Page<User> getAllUsers(String query, PageRequest pageRequest, String date, Integer orders, Boolean hasNewsLetter, String segment){
        Set<User> usersSet = new HashSet<>();
        usersSet.addAll(userRepository.findAll());
        if(query!=null && !query.isEmpty()){
            usersSet.retainAll(userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query));
        }
        if(date!=null && !date.isEmpty()){
            LocalDate today = LocalDate.now();
            LocalDate dateFrom = LocalDate.MIN;
            LocalDate dateTo = LocalDate.MAX;
            switch(date){
                case "Today":
                    dateFrom = today;
                    dateTo = today;
                    break;
                case "This week":
                    dateFrom = today.minusWeeks(1);
                    dateTo = today;
                    break;
                case "Last week":
                    dateFrom = today.minusWeeks(2);
                    dateTo = today.minusWeeks(1);
                    break;
                case "This month":
                    dateFrom = today.minusMonths(1);
                    dateTo = today;
                    break;
                case "Last month":
                    dateFrom = today.minusMonths(2);
                    dateTo = today.minusMonths(1);
                    break;
                case "Earlier":
                    dateFrom = LocalDate.MIN;
                    dateTo = today.minusMonths(2);
                    break;
            }
            usersSet.retainAll(userRepository.findByLastLoginTimeBetween(dateFrom, dateTo));
        }
        if(orders!=null){
            if(orders==0){
                usersSet.retainAll(userRepository.findByOrders(orders));
            }else{
                usersSet.retainAll(userRepository.findByOrdersGreaterThan(orders));
            }
        }if(hasNewsLetter!=null){
            usersSet.retainAll(userRepository.findByHasNewsletter(hasNewsLetter));
        }if(segment!=null){
            // List<Segment> segments = segmentRepository.findByName(segment);
            // int segmentId = segments.get(0).getId();
            List<Segment> segmentList = segmentRepository.findByName(segment);
            // List<UserSegment> userSegments = userSegmentRepository.findBySegment(segmentObject.get(0));

            if(!segmentList.isEmpty()){
                Segment targetSegment = segmentList.get(0); // 取第一個
                usersSet.retainAll(userRepository.findByUserSegment(targetSegment));
            }           
        }
        
        if (usersSet.isEmpty()){
            return null;
        }
        else{
            List<User> resultList = new ArrayList<>(usersSet);
            Page<User> results = new PageImpl<>(resultList, pageRequest, resultList.size());
            return results;
        }  
    }

    

    public Page<User> getAllUsers2(String query, Boolean hasNewletter, Integer segmentId, PageRequest pageRequest){
        Specification<User> spec = userSpecification(query, hasNewletter, segmentId);
        
        return userRepository.findAll(spec, pageRequest);
    }

    private Specification<User> userSpecification(String queryName, Boolean hasNewsletter, Integer sementId){
        return ((root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            if(queryName!=null&& !queryName.isEmpty()){
                predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%"+queryName.toLowerCase()+"%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%"+queryName.toLowerCase()+"%")
                    ));
            }

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        });
    }

    public GetUserResponse updateUser(int id, UpdateUserRequest request){
        Optional<User> user = userRepository.findById(id);

         if(user.isPresent()){
            User userToBeUpdated = user.get();
            userToBeUpdated.setFirstName(request.getFirstName());
            userToBeUpdated.setLastName(request.getLastName());
            userToBeUpdated.setEmail(request.getEmail());
            if(request.getBirthday()!=null){
                userToBeUpdated.setBirthday(request.getBirthday());
            }
            if(!request.getAddress().isEmpty()){
                userToBeUpdated.setAddress(request.getAddress());
            }
            if(!request.getCity().isEmpty()){
                userToBeUpdated.setCity(request.getCity());
            }
            if(!request.getState().isEmpty()){
                userToBeUpdated.setState(request.getState());
            }
            userToBeUpdated.setZipcode(request.getZipcode());
            userToBeUpdated.setHasNewsletter(request.isHasNewsletter());
            // userToBeUpdated.setUserSegments(request.getUserSegments());

            User savedUser = userRepository.save(userToBeUpdated);
            GetUserResponse response = new GetUserResponse(savedUser.getFirstName(), savedUser.getLastName(),
                savedUser.getEmail(), savedUser.getBirthday(), savedUser.getAddress(), savedUser.getCity(),
                savedUser.getState(), savedUser.getZipcode(), savedUser.isHasNewsletter(),
                savedUser.getUserSegments(), savedUser.getLastLoginTime(), savedUser.isDeleted());
            return response;
        }else{
            return null;
        }
    }

}
