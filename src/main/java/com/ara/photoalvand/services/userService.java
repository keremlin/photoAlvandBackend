package com.ara.photoalvand.services;

import java.util.NoSuchElementException;

import com.ara.photoalvand.models.User;
import com.ara.photoalvand.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class userService {
    @Autowired
    private  UserRepository repo;

    public userService(){
        super();
        System.out.println("user service is loaded -----------------------S");
    }
    public User findUserByUsername(String username) throws NoSuchElementException {
        return repo.findByUsername(username).get();
    }
    
}
