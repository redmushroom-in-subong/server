package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
