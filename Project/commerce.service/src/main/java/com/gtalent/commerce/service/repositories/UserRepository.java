package com.gtalent.commerce.service.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gtalent.commerce.service.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    //SELECT * FROM users Where username=???
    // Optional<User> findByUsername(String username); 
}
