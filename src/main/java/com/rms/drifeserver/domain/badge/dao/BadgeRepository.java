package com.rms.drifeserver.domain.badge.dao;

import com.rms.drifeserver.domain.badge.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.TypedQuery;
import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
    @Query("select bc,b from BadgeCode bc left join bc.badges b where b.userId=:userId or b.userId is null  ")
    List findAllUserBadges(Long userId);
}
