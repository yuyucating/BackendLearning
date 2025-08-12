package com.example.demo.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    //SELECT * FROM users Where username=???
    Optional<User> findByUsername(String username); 

}
