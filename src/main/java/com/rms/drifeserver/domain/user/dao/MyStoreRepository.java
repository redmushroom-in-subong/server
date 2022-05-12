package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.User;
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
    //TODO 단골 손님에 대한 기준, 현재 쿼리로 알아내야 할 지 이벤트 기반으로 단골 손님을 넣어야 할지
    // - 단골수가 자주 쓰이기 때문에 따로저장이 맞을것같음
    //TODO store에 thumbnail 필드 필요
    @Query("select distinct new com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse(" +
            "s.id," +
            "s.storeName," +
            "'empty',"+
            "(select count(user) from User user where 10<(select count(u) from Visit v join v.user u where v.store.id=s.id ) )," +
            "s.visits.size," +
            "(l.store is not null) )" +
            "from Store s join s.visits v left join s.likes l " +
//            "group by v.store" +
            "where v.user.id=:userId order by v.updatedAt")
    List<MyStoreResponse> findRecentStore(@Param("userId") Long userId);
    @Query("select u from User u ")
    List<MyStoreResponse> findFrequentStore(@Param("userId") Long userId);
    @Query("select distinct new com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse(" +
            "s.id," +
            "s.storeName," +
            "'empty',"+
            "(select count(user) from User user where 10<(select count(u) from Visit v join v.user u where v.store.id=s.id ) )," +
            "s.visits.size," +
            "(l.store is not null) )" +
            "from Store s join s.likes l   " +
            "where l.user.id=:userId ")
    List<MyStoreResponse> findFavoriteStore(@Param("userId") Long userId);
}
