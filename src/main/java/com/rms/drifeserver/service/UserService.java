package com.rms.drifeserver.service;

import com.rms.drifeserver.model.User;
import com.rms.drifeserver.repository.JdbcUserRepository;
import com.rms.drifeserver.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserService {
    //push test
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public List<User> findAllUsers(){
        return userRepository.getAllUsers();
    }

    public Optional<User> findUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId);
    }
    public Integer saveUser(User user){
        return userRepository.createUser(user);
    }
}
