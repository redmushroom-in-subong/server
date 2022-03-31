package com.rms.drifeserver.domain.badge.dao;

import com.rms.drifeserver.domain.badge.model.Badge;
import com.rms.drifeserver.domain.badgecode.model.BadgeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
    @Query(value="select bc.id as badgeId , bc.badgeName as badgeName,(b.user.id is not null) as isActive " +
            "from BadgeCode bc left join bc.badges b " +
            "where b.user.id=:userId or b.user.id is null  ")
    List<Map<String,Object>> findAllUserBadges(Long userId);
    @Query(value="select badge from BadgeCode badge where badge.reviewCount=:reviewCount")
    List<BadgeCode> findNextBadge(@Param("reviewCount") int reviewCount);
}
