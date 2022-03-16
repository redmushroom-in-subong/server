package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.Visit;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    long countByStore(Store store);

    long countByStoreAndUser(Store store, User user);

    @Query("select count(r) from Review r where r.store = :store group by r.user.id having count(r) >= 10")
    long countByStoreWithCustom(@Param("store") Store store);
}
