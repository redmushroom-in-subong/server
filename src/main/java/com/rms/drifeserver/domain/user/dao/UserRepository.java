package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers();
    Integer createUser(User user);
    Integer updateUser(User user,String userId);
    Integer deleteUser(String userId);
}
