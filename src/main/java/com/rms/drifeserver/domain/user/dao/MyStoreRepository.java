package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.response.MyRecentStoreResponse;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MyStoreRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    //TODO 단골 손님에 대한 기준, 현재 쿼리로 알아내야 할 지 이벤트 기반으로 단골 손님을 넣어야 할지
    // - 단골수가 자주 쓰이기 때문에 따로저장이 맞을것같음
    //TODO store에 thumbnail 필드 필요

    /**
     * this.storeId = storeId;
     *         this.storeName = storeName;
     *         this.visitedAt = visitedAt.toString().substring(0,10);
     *         this.thumbnailImage = thumbnailImage;
     *         this.regularCustomerCount = regularCustomerCount;
     *         this.visitCount = visitCount;
     *         this.isLike = isLike;
     * @param userId
     * @return
     */
    @Query(value="select " +
            "distinct(v.Id) as visitId," +
            "s.id as storeId," +
            "s.storeName as storeName," +
            "v.updatedAt as visitedAt," +
            "'empty' as thumbnailImage,"+
            "(select count(user) from User user where 10<(select count(u) from Visit v join v.user u where v.store.id=s.id ))" +
            "as regularCustomerCount ," +
            "s.visits.size as visitCount," +
            "(l.store is not null) as isLike " +
            "from Store s join s.visits v left join s.likes l " +
            "where v.user.id=:userId order by v.updatedAt"


    )
    List<MyRecentStoreResponse> findRecentStore(@Param("userId") Long userId);
    @Query("select u from User u ")
    List<MyStoreResponse> findFrequentStore(@Param("userId") Long userId);
    @Query("select " +
            "s.id as storeId," +
            "s.storeName as storeName," +
            "'empty' as thumbnailImage,"+
            "(select count(user) from User user where 10<(select count(u) from Visit v join v.user u where v.store.id=s.id ) )" +
            "as regularCustomerCount," +
            "s.visits.size as visitCount," +
            "(l.store is not null) as isLike " +
            "from Store s join s.likes l   " +
            "where l.user.id=:userId ")
    List<MyStoreResponse> findFavoriteStore(@Param("userId") Long userId);
}
