package com.gtalent.commerce.service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.CreateUserRequest;
import com.gtalent.commerce.service.requests.UpdateUserRequest;
import com.gtalent.commerce.service.responses.GetUserListResponse;
import com.gtalent.commerce.service.responses.GetUserResponse;

@RestController
@RequestMapping("v1/users")
@CrossOrigin("*")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setHasNewsletter(request.getHasNewsletter());

        User newUser = userRepository.save(user);

        GetUserResponse response = new GetUserResponse(newUser.getFirstName(), newUser.getLastName(),
            newUser.getEmail(), newUser.getBirthday(), newUser.getAddress(), newUser.getCity(),
            newUser.getState(), newUser.getZipcode(), newUser.isHasNewsletter(),
            newUser.getUserSegments(), newUser.getLastLoginTime(), newUser.isDeleted());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GetUserListResponse>> getAllUsers(){
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users.stream().map(GetUserListResponse::new).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetUserResponse> updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request){
        // 為了先檢查資料是否存在!
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User userToBeUpdated = user.get();
            userToBeUpdated.setFirstName(request.getFirstName());
            userToBeUpdated.setLastName(request.getLastName());
            userToBeUpdated.setEmail(request.getEmail());
            userToBeUpdated.setBirthday(request.getBirthday());
            userToBeUpdated.setAddress(request.getAddress());
            userToBeUpdated.setCity(request.getCity());
            userToBeUpdated.setState(request.getState());
            userToBeUpdated.setZipcode(request.getZipcode());
            userToBeUpdated.setHasNewsletter(request.isHasNewsletter());

            User savedUser = userRepository.save(userToBeUpdated);
            GetUserResponse response = new GetUserResponse(savedUser.getFirstName(), savedUser.getLastName(),
                savedUser.getEmail(), savedUser.getBirthday(), savedUser.getAddress(), savedUser.getCity(),
                savedUser.getState(), savedUser.getZipcode(), savedUser.isHasNewsletter(),
                savedUser.getUserSegments(), savedUser.getLastLoginTime(), savedUser.isDeleted());
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setDeleted(true);
            userRepository.save(user.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
