package com.rms.drifeserver.domain.badge.service.impl;

import com.rms.drifeserver.domain.badge.event.UserBadgeEvent;
import com.rms.drifeserver.domain.badgecode.dao.BadgeCodeRepository;
import com.rms.drifeserver.domain.badge.dao.BadgeRepository;
import com.rms.drifeserver.domain.badge.model.Badge;
import com.rms.drifeserver.domain.badgecode.model.BadgeCode;
import com.rms.drifeserver.domain.badge.service.BadgeService;
import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;
import com.rms.drifeserver.domain.badgecode.service.BadgeCodeService;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.rms.drifeserver.domain.common.exception.type.ErrorCode.INVALID_BADGE_CODE;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {
    final private UserService userService;
    final private BadgeRepository badgeRepository;
    final private BadgeCodeService badgeCodeService;
    @Override
    @Transactional
    public void checkBadgeEarnCondition(Long id) throws BaseException {
        User user=userService.getUserById(id);
        int reviewCount=user.getMyReviewList().size();
        List<BadgeCode> EarnedBadges=badgeRepository.findNextBadge(reviewCount+1);
        for(BadgeCode earned:EarnedBadges){
            //TODO 혹시 여러개 얻어야할 상황에 대비해서 loop , 현재 무조건 1번수행
            Badge badge=new Badge();
            badge.setBadgeCode(earned);
            badge.setUser(user);
            badgeRepository.save(badge);
            System.out.println(
                              "#################################### " +
                            "\n########## congratulation!! ########" +
                            "\nuser (id:"+id+") earned badge named "+badge.getBadgeCode().getBadgeName());
        }
    }
    @Override
   public void addBadge(Long userId,Long badgeId) throws Exception {
        User user=new User();
        user.setId(userId);
        Badge badge=new Badge(0l,new BadgeCode(),user);
        Optional<BadgeCode> badgeCode = badgeCodeService.findById(badgeId);
        badge.setBadgeCode(badgeCode.get());
        badgeRepository.save(badge);
    }

    @Override
    public List<UserBadgeResponse> findAllUserBadges(){
        User user=userService.getUserEntity();
        List<Map<String,Object>> result=badgeRepository.findAllUserBadges(user.getId());
        List<UserBadgeResponse> ret = new ArrayList<>();
        for (Map<String,Object> a:result){
            ret.add(UserBadgeResponse.of(a));
        }
        return ret;
    }

}
