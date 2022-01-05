package com.rms.drifeserver.repository;

import com.rms.drifeserver.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers();
    Optional<User> findUserByUserId(String userId);
    Optional<User> findUserByUserSeq(Integer userSeq);
    Optional<User> createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
