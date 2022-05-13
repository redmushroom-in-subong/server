package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.response.MyRecentStoreResponse;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyStoreRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    //TODO store에 thumbnail 필드 필요
    @Query("select distinct new com.rms.drifeserver.domain.user.service.dto.response.MyRecentStoreResponse(" +
            "s.id," +
            "s.storeName," +
            "max(v.updatedAt)," +
            "'empty'," +
            "coalesce( (select count(distinct v.user.id) from Visit v where v.store.id=s.id group by v.user.id having (count(v)>10)),0L) ," +
            "s.visits.size," +
            "s.reviews.size," +
            "(l.store is not null) )" +
            "from Store s join s.visits v left join s.likes l " +
            "where v.user.id=:userId  " +
            "group by s"
            )
    List<MyRecentStoreResponse> findRecentStore(@Param("userId") Long userId);
    @Query("select distinct new com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse(" +
            "s.id," +
            "s.storeName," +
            "'empty'," +
            "coalesce( (select count(distinct v.user.id) from Visit v where v.store.id=s.id group by v.user.id having (count(v)>10)),0L) ," +
            "s.visits.size," +
            "s.reviews.size,"+
            "(l.store is not null) )" +
            "from Store s join s.visits v left join s.likes l " +
            "where v.user.id=:userId " +
            "group by s having(s.visits.size>10)")
    List<MyStoreResponse> findFrequentStore(@Param("userId") Long userId);
    @Query("select distinct new com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse(" +
            "s.id," +
            "s.storeName," +
            "'empty', " +
            "coalesce( (select count(distinct v.user.id) from Visit v where v.store.id=s.id group by v.user.id having (count(v)>10)),0L) ," +
            "s.visits.size," +
            "s.reviews.size," +
            "(l.store is not null) )" +
            "from Store s join s.likes l   " +
            "where l.user.id=:userId ")
    List<MyStoreResponse> findFavoriteStore(@Param("userId") Long userId);
}
