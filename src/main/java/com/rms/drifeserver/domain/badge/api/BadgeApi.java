package com.rms.drifeserver.domain.badge.api;

import com.rms.drifeserver.domain.badge.service.BadgeService;
import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/badge")
@RequiredArgsConstructor
public class BadgeApi {
    final private UserService userService;
    final private BadgeService badgeService;
    @PostMapping("")
    @ResponseBody
    public ApiResponse addBadgeCode(){
        try{
            List<UserBadgeResponse> ret=badgeService.findAllUserBadges();
            return ApiResponse.success(ret);
        }catch (BaseException baseException){
            return ApiResponse.error(baseException.getErrorCode());
        }catch (Exception exception){
            System.out.println("unhandled exception :" + exception.getMessage());
            exception.printStackTrace();
            return ApiResponse.error(ErrorCode.INVALID);
        }
    }
    @PutMapping("/{badgeId}")
    @ResponseBody
    public ApiResponse editBadge(@PathVariable Long badgeId){
        try{
            userService.editUsingBadge(badgeId);
            return ApiResponse.success("success");
        }catch (BaseException baseException){
            return ApiResponse.error(baseException.getErrorCode());
        }catch (Exception exception){
            System.out.println("unhandled exception :" + exception.getMessage());
            exception.printStackTrace();
            return ApiResponse.error(ErrorCode.INVALID);
        }
    }
}
