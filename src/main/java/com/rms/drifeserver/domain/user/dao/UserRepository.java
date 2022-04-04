package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.store.service.dto.response.UserInStoreResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.response.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUsername(String username);

    @Query("select u from User u where (select count(v) from Visit v where u=v.user and v.store.id=:storeId order by count(v) desc)>=10")
    List<User> findRegCustom(@Param("storeId") Long storeId);
}
