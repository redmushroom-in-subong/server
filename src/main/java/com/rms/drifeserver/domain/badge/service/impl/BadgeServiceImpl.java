package com.rms.drifeserver.domain.badge.service.impl;

import com.rms.drifeserver.domain.badge.dao.BadgeCodeRepository;
import com.rms.drifeserver.domain.badge.dao.BadgeRepository;
import com.rms.drifeserver.domain.badge.model.Badge;
import com.rms.drifeserver.domain.badge.model.BadgeCode;
import com.rms.drifeserver.domain.badge.service.BadgeService;
import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {
    final private UserService userService;
    final private BadgeRepository badgeRepository;
    final private BadgeCodeRepository badgeCodeRepository;
    @Override
    public void checkBadgeEarnCondition() throws BaseException {
        User user=userService.getUserEntity();
        int reviewCount=user.getMyReviewList().size();
       // List<Badge> userBadgeList=user.getMyBadgeList();
        List<BadgeCode> allBadgeList=badgeCodeRepository.findAll();
//        List<BadgeCode>
        //추가
    }
    @Override
   public void addBadge(Long userId,Long badgeId){
        Badge badge=new Badge(0l,new BadgeCode(),userId);
        badge.getBadgeCode().setId(badgeId);
        badgeRepository.save(badge);
    }

    @Override
    public List<UserBadgeResponse> findAllUserBadges(Long userId){
        List<UserBadgeResponse> ret=new ArrayList<>();
        List userBadgeResults= badgeRepository.findAllUserBadges(userId);
        BadgeCode badgeCode;
        Badge badge;
        for(Object item:userBadgeResults){
            Object[] result=(Object[])item;
            System.out.println("badgeCode = " + result[0]+"\nbadge"+result[1]);
            boolean isNotNull=(result[1]!=null);
            badge=(Badge) result[1];
            badgeCode=(BadgeCode) result[0];
            System.out.println("badge log = " + badge+" , "+badgeCode);
            ret.add(new UserBadgeResponse(badgeCode.getId(),badgeCode.getBadgeName(),isNotNull));
        }
        return ret;
    }

}
