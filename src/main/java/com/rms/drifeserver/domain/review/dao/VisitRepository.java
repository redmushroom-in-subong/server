package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.Visit;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    Long countByStore(Store store);

    Long countByStoreAndUser(Store store, User user);

    @Query(nativeQuery = true, value = "select count(*) from (select user_id from visit where store_id = :storeId group by user_id having count(*) >= 10) as custom")
    Long countByStoreWithCustom(@Param("storeId") Long storeId);
}
