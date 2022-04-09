package com.rms.drifeserver.domain.like.dao;

import com.rms.drifeserver.domain.like.model.StoreLikes;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLikesRepository extends JpaRepository<StoreLikes,Long> {

    StoreLikes findByUserAndStore(User user, Store store);
}
