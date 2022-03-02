package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
