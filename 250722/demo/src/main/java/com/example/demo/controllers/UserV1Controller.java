package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
@RequestMapping("/v1/users")
@CrossOrigin("*")
public class UserV1Controller {
    /*
    // 由建構子將資料庫溝通套件注入
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserV1Controller(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }*/

    //由 annotation 將資料庫溝通套件注入
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
        String sql = "insert into users (username, email) values (?,?)";
        int rowsAffected = jdbcTemplate.update(sql, request.getUsername(), request.getEmail()); //第二個參數開始塞值到第一個參數(sql 的 ?)
        // rowsAffected 回傳 影響了幾行資料

        // 如果 rowsAffected > 0 代表有被影響的資料, 代表執行成功
        if(rowsAffected > 0){
            CreateUserResponse response = new CreateUserResponse(request.getUsername());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<GetUsersResponse>> getAllUsers (){
        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

        return ResponseEntity.ok(users.stream().map(GetUsersResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUsersResponse> getUserById(@PathVariable int id){
        String sql = "SELECT * FROM users where id=?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);    
            return ResponseEntity.ok(new GetUsersResponse(user));
        // } catch (Exception e) {
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }        
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetUsersResponse>> getUserByKeyword(@RequestParam String keyword){
        String sql = "SELECT * FROM users WHERE username LIKE ?";
        try {
            List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), '%'+keyword+'%');
            return ResponseEntity.ok(users.stream().map(GetUsersResponse::new).toList());
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
        public ResponseEntity<UpdateUserResponse> updateUserById(@PathVariable int id, @RequestBody UpdateUserRequest request){
        String sql = "UPDATE users SET username=? WHERE id=?";
        int rowsAffected = jdbcTemplate.update(sql, request.getUsername(), id);

        if(rowsAffected > 0){
            UpdateUserResponse response = new UpdateUserResponse(request.getUsername());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id){
        String sql = "DELETE FROM users WHERE id=?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if(rowsAffected > 0){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
