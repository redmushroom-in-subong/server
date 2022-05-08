package com.rms.drifeserver.domain.oauth.service;

import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.oauth.entity.UserPrincipal;
import com.rms.drifeserver.domain.user.service.UserService;
import com.rms.drifeserver.domain.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can not find username.");
        }
        return UserPrincipal.create(user);
    }
}
