package com.rms.drifeserver.domain.badge.service.impl;

import com.rms.drifeserver.domain.badge.event.UserBadgeEvent;
import com.rms.drifeserver.domain.badgecode.dao.BadgeCodeRepository;
import com.rms.drifeserver.domain.badge.dao.BadgeRepository;
import com.rms.drifeserver.domain.badge.model.Badge;
import com.rms.drifeserver.domain.badgecode.model.BadgeCode;
import com.rms.drifeserver.domain.badge.service.BadgeService;
import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {
    final private UserService userService;
    final private BadgeRepository badgeRepository;
    final private ApplicationEventPublisher publisher;
    @Override
    @Transactional
    public void checkBadgeEarnCondition(Long id) throws BaseException {
        User user=userService.getUserById(id);
        int reviewCount=user.getMyReviewList().size();
        List<BadgeCode> EarnedBadges=badgeRepository.findNextBadge(reviewCount+1);
        for(BadgeCode earned:EarnedBadges){
            Badge badge=new Badge();
            badge.setBadgeCode(earned);
            badge.setUser(user);
            badgeRepository.save(badge);
        }
    }
    @Override
   public void addBadge(Long userId,Long badgeId){
        User user=new User();
        user.setId(userId);
        Badge badge=new Badge(0l,new BadgeCode(),user);
        badge.getBadgeCode().setId(badgeId);
        badgeRepository.save(badge);
    }

    @Override
    public List<UserBadgeResponse> findAllUserBadges(){
        User user=userService.getUserEntity();
        publisher.publishEvent(new UserBadgeEvent(this,user.getId()));

        List<Map<String,Object>> result=badgeRepository.findAllUserBadges(user.getId());
        List<UserBadgeResponse> ret = new ArrayList<>();
        for (Map<String,Object> a:result){
            ret.add(UserBadgeResponse.of(a));
        }
        return ret;
    }

}
