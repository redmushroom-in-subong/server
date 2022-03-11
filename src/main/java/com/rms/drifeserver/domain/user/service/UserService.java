package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import com.rms.drifeserver.domain.user.service.dto.request.EditProfileReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long uId)throws BaseException{
        return userRepository.findById(uId).get();
    }
    public User getUserByUserId(String userId) throws BaseException {
        if(userId=="234"){
            throw new BaseException(ErrorCode.BAD_GATEWAY);
        }
        return userRepository.findByUserId(userId);
    }
    public User getUserEntity() throws BaseException{
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = getUserByUserId(principal.getUsername());
        return user;
    }
    @Transactional
    public void EditUserProfile(EditProfileReq editProfileReq) throws BaseException{
        User user=getUserEntity();
        user.setUsername(editProfileReq.getNickname());
        user.setProfileImageUrl(editProfileReq.getProfileImageUrl());
    }
    public boolean checkExistence(String username){
        if(userRepository.findByUsername(username)==null) return false;
        return true;
    }
}
