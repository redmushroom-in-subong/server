package com.rms.drifeserver.domain.oauthlogin.api.service;

import com.rms.drifeserver.domain.oauthlogin.api.entity.user.User;
import com.rms.drifeserver.domain.oauthlogin.api.repository.user.UserRepository;
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
