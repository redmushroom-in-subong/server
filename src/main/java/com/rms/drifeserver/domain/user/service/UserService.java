package com.rms.drifeserver.domain.user.service;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.request.EditProfileReq;
import com.rms.drifeserver.domain.user.service.dto.request.EditRegionReq;

public interface UserService {
     User getUserById(Long uId);
     User getUserByUserId(String userId) throws BaseException ;
     User getUserEntity() throws BaseException;
     void editUserProfile(EditProfileReq editProfileReq) throws BaseException;
     void editUserRegion(EditRegionReq editRegionReq) throws BaseException;
     void editUsingBadge(Long badgeCodeId)throws Exception;
     boolean checkExistence(String username);
}
