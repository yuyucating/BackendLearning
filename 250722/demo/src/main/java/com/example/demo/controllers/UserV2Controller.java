package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.CreateUserRequest;
import com.example.demo.requests.UpdateUserRequest;
import com.example.demo.responses.GetUsersResponse;
import com.example.demo.responses.UpdateUserResponse;
import com.example.demo.responses.UserResponse;


// 不使用 jdbc 使用 orm
@RestController
@RequestMapping("v2/users")
@CrossOrigin("*")
public class UserV2Controller {

    private final UserRepository userRepository;

    @Autowired
    public UserV2Controller(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<GetUsersResponse>> getAllUsers (){
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users.stream().map(GetUsersResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id){
        // User user = userRepository.getReferenceById(id);    
        // return ResponseEntity.ok(new GetUsersResponse(user));
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){ // 如果這個 user 存在
            UserResponse response = new UserResponse(user.get());
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }        
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<GetUsersResponse>> getUserByKeyword(@RequestParam String keyword){
    //     try{

    //     } catch (Exception e){
    //         return ResponseEntity.notFound().build();
    //     }
    //     //尚未完成
    // }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request){

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail()); 
        
        User userNew = userRepository.save(user);

        UserResponse response = new UserResponse(userNew.getUsername(), userNew.getEmail(), userNew.getRole());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
        public ResponseEntity<UpdateUserResponse> updateUserById(@PathVariable int id, @RequestBody UpdateUserRequest request){
        
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User updatedUser = user.get(); // 將欲更新資料填充到 User
            updatedUser.setUsername(request.getUsername());
            User savedUser = userRepository.save(updatedUser); // mySQL 儲存後的 User
            UpdateUserResponse response = new UpdateUserResponse(savedUser.getUsername()); 
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }

}
