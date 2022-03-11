package com.rms.drifeserver.domain.badge.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("v1/badge")
@RequiredArgsConstructor
public class BadgeCodeApi {
    final private UserService userService;
    @PostMapping("")
    @ResponseBody
    public ApiResponse addBadgeCode(){
        try{
            User user = userService.getUserEntity();
            return ApiResponse.success(user);
        }catch (BaseException baseException){
            return ApiResponse.error(baseException.getErrorCode());
        }catch (Exception exception){
            System.out.println("unhandled exception :" + exception.getMessage());
            exception.printStackTrace();
            return ApiResponse.error(ErrorCode.INVALID);
        }
    }
}
