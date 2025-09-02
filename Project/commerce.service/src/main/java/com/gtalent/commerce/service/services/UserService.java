package com.gtalent.commerce.service.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.UserRepository;


// 邏輯判斷在這裡
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
            return new ArrayList<User>();
        }
    }

}
