package com.rms.drifeserver.domain.user.dao;

import java.util.Optional;

public interface FindUserDao {
    Optional<User> findUserByUserId(String userId);
    Optional<User> findUserByUserSeq(Integer userSeq);
}
