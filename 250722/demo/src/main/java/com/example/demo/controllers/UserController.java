package com.example.demo.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.requests.CreateUserRequest;
import com.example.demo.requests.UpdateUserRequest;
import com.example.demo.responses.CreateUserResponse;
import com.example.demo.responses.GetUsersResponse;
import com.example.demo.responses.UpdateUserResponse;



@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final Map<Integer, User> mockUser = new HashMap<>(); //建立一個假的資料庫
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public UserController(){
        mockUser.put(1, new User(1, "admin", "admin@gmail.com"));
        mockUser.put(2, new User(2, "user1", "user1@gmail.com"));
        mockUser.put(3, new User(3, "user2", "user2@gmail.com"));

        atomicInteger.set(4);
    }

    @GetMapping()
    public ResponseEntity<List<GetUsersResponse>> getAllUsers(){
        List<User> userList = new ArrayList<>(mockUser.values());
        List<GetUsersResponse> responseList = new ArrayList<>();

        for (User user: userList) {
            // User user = mockUser.get(i);
            GetUsersResponse response = new GetUsersResponse(user.getId(), user.getUsername());
            responseList.add(response);
        }
        
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUsersResponse> getUserById(@PathVariable int id){
        GetUsersResponse user = new GetUsersResponse(mockUser.get(id).getId(), mockUser.get(id).getUsername());

        if(user == null){
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    /* @PutMapping("/{id}")
    // public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User request){
    //     User user = mockUser.get(id);
    //     if(user == null){
            
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
    //     }

    //     System.out.println(request.getUsername());
    //     System.out.println(request.getEmail());

    //     user.setUsername(request.getUsername());
    //     user.setEmail(request.getEmail());

    //     return ResponseEntity.status(HttpStatus.OK).body(user);
    // }/* */

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUserById(@PathVariable int id, @RequestBody UpdateUserRequest request){
        User user = mockUser.get(id);
        if(user == null){
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        System.out.println(request.getUsername());
        // System.out.println(request.getEmail());

        user.setUsername(request.getUsername());
        // user.setEmail(request.getEmail());
        UpdateUserResponse response = new UpdateUserResponse(user.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/createuser") // add User by manual ID
    public ResponseEntity<User> createUsers(@RequestBody User newUser){
        User user  = new User(newUser.getId(), newUser.getUsername(), newUser.getEmail());
        mockUser.put(newUser.getId(), user);
        // System.out.println(newUser.getUsername());

        return ResponseEntity.ok(user);
    }

    @PostMapping() // add User by auto ID
    public ResponseEntity<CreateUserResponse> createUserByAutoID(@RequestBody CreateUserRequest newUser){
        // private final Map<>
        int newID = atomicInteger.getAndIncrement();
        User user  = new User(newID, newUser.getUsername(), newUser.getEmail());
        mockUser.put(newID, user);
        CreateUserResponse response = new CreateUserResponse(user.getUsername());

        // return ResponseEntity.status(HttpStatus.CREATED).body(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id){
        User user = mockUser.get(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        mockUser.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetUsersResponse>> searchUser (@RequestParam String keyword){
        /*List<User> results = mockUser
        .values() //物件裡面的屬性轉為 List
        .stream() // Lambda 起手式
        .filter(user -> // 遍歷 List<User> results
        user.getUsername().toLowerCase().contains(keyword.toLowerCase())).toList();

        List<GetUsersResponse> responseList = new ArrayList<>();

        for (User user: results) {
            // User user = mockUser.get(i);
            GetUsersResponse response = new GetUsersResponse(user.getId(), user.getUsername());
            responseList.add(response);
        }
            return ResponseEntity.ok(responseList);*/

        List<GetUsersResponse> results = mockUser
        .values()
        .stream()
        .filter(user -> 
        user.getUsername().toLowerCase().contains(keyword.toLowerCase())) //過去 mockUser 裡面的 user: 對應 contain
        // -> 取得結果為 Ture 的 user!!! mapping(??) 成 GetUserResponse
        // .map(user -> toGetUsersResponse(user)).toList();
        // .map(this::toGetUsersResponse).toList();
        .map(user -> new GetUsersResponse(user))// 有幾個結果為 true 的 user 就走幾次~
        // = .map(GetUserResponse::new)
        .toList(); 

        return ResponseEntity.ok(results);
    }

    @GetMapping("/normal")
    public ResponseEntity<List<GetUsersResponse>> searchNormalUser(){
        List<GetUsersResponse> results = mockUser.values()
        .stream()
        .filter(user ->
        !user.getUsername().toLowerCase().contains("admin"))
        .map(user -> new GetUsersResponse(user)).toList();

        return ResponseEntity.ok(results);
    }

    private GetUsersResponse toGetUsersResponse (User user){
        return new GetUsersResponse(user.getId(), user.getUsername());
    }

    
}
