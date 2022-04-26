package com.ara.photoalvand.services;

import java.util.NoSuchElementException;

import com.ara.photoalvand.models.ERole;
import com.ara.photoalvand.models.Role;
import com.ara.photoalvand.models.User;
import com.ara.photoalvand.repository.RoleRepository;
import com.ara.photoalvand.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    private  UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
	PasswordEncoder encoder;

    public userService(){
        super();
        System.out.println("user service is loaded -----------------------S");
    }
    public User findUserByUsername(String username) throws NoSuchElementException {
        return repo.findByUsername(username).get();
    }
    
    public boolean initRoles() {
        if (!roleRepo.findByName(ERole.ROLE_ADMIN).isPresent())
            roleRepo.save(new Role(ERole.ROLE_ADMIN));
        if (!roleRepo.findByName(ERole.ROLE_MODERATOR).isPresent())
            roleRepo.save(new Role(ERole.ROLE_MODERATOR));
        if (!roleRepo.findByName(ERole.ROLE_USER).isPresent())
            roleRepo.save(new Role(ERole.ROLE_USER));
        return true;
    }
    public boolean initAdminUser(){
        if(!repo.findByUsername("admin").isPresent()){
            User user=new User("admin","admin@photo.com",encoder.encode("123123"));
            user.setRole(roleRepo.findByName(ERole.ROLE_ADMIN).get());
            repo.save(user);
        }
        return true;
    }
    
}
