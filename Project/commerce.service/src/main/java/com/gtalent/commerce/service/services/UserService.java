package com.gtalent.commerce.service.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.UpdateUserRequest;
import com.gtalent.commerce.service.responses.GetUserResponse;


// 邏輯判斷在這裡
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        try{
            List<User> users = userRepository.findAll();

            return users;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }

    public Page<User> getAllUsers(String query, PageRequest pageRequest){
        if(query!=null && !query.isEmpty()){
            return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query, pageRequest);
        }else{
            return userRepository.findAll(pageRequest);
        }

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
