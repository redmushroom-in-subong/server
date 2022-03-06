package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long uId){return userRepository.findById(uId).get();}
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
    public User getUserEntity(){
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = getUserByUserId(principal.getUsername());
        return user;
    }
}
