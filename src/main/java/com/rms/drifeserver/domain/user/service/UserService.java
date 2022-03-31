package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.badge.model.Badge;
import com.rms.drifeserver.domain.badgecode.model.BadgeCode;
import com.rms.drifeserver.domain.badgecode.service.BadgeCodeService;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import com.rms.drifeserver.domain.user.service.dto.request.EditProfileReq;
import com.rms.drifeserver.domain.user.service.dto.request.EditRegionReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.rms.drifeserver.domain.common.exception.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BadgeCodeService badgeCodeService;
    public User getUserById(Long uId){
        if(userRepository.findById(uId).isEmpty()) throw new BaseException(NOTFOUND_USER);
        return userRepository.findById(uId).get();
    }
    public User getUserByUserId(String userId) throws BaseException {
        if(userRepository.findByUserId(userId).isEmpty())  throw new BaseException(NOTFOUND_USER);
        return userRepository.findByUserId(userId).get();
    }
    public User getUserEntity() throws BaseException{
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = getUserByUserId(principal.getUsername());
        return user;
    }
    @Transactional
    public void editUserProfile(EditProfileReq editProfileReq) throws BaseException{
        User user=getUserEntity();
        if(checkExistence(editProfileReq.getNickname())) throw new BaseException(CONFLICT_NICKNAME);
        user.setUsername(editProfileReq.getNickname());
        user.setProfileImageUrl(editProfileReq.getProfileImageUrl());
    }
    @Transactional
    public void editUserRegion(EditRegionReq editRegionReq) throws BaseException{
        User user=getUserEntity();
        //TODO 한국 좌표 범위 적용?
        user.setRegionName(editRegionReq.getRegionName());
        user.setRegionCode(editRegionReq.getRegionCode());
    }
    @Transactional
    public void editUsingBadge(Long badgeCodeId)throws Exception{
        Optional<BadgeCode> badgeCode = badgeCodeService.findById(badgeCodeId);
        if(badgeCode.isPresent()){
            User user=getUserEntity();
            Long target=-1L;
            //TODO 보유한 뱃지 아이디 (target) 리턴하는 함수로 빼기

          //  System.out.println("intended N+1 prob");
            for (Badge badge : user.getMyBadgeList()) {
            //    System.out.println("for 1 time"+badge.getBadgeCode().getBadgeName());
                if(badge.getBadgeCode().equals(badgeCode.get())){
                    target=badge.getId();
                }
            }
            if(!target.equals(-1L)){
                Badge toEdit = new Badge();
                toEdit.setId(target);
                user.setMyBadge(toEdit);

            }else{
                System.out.println("error badge not exist");
                throw new BaseException(NOTFOUND_BADGE);
            }
        }else{
            System.out.println("error invalid badgeId");
            throw new BaseException(INVALID_BADGE_CODE);

        }

    }
    public boolean checkExistence(String username){
        return userRepository.findByUsername(username).isPresent();
    }
}
