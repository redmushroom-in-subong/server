package com.rms.drifeserver.domain.user.api;

import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import com.rms.drifeserver.domain.user.service.dto.request.EditProfileReq;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserApi {
    final private UserService userService;
    @GetMapping({"", "/{uId}"})
    @ResponseBody
    public ApiResponse getUserInfo(@PathVariable(required = false) Long uId){
        try{
            if(uId!=null){
                return ApiResponse.success(userService.getUserById(uId));
            }
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
    @PutMapping("")
    @ResponseBody
    public ApiResponse editUserInfo( @RequestBody EditProfileReq editReq){
        try{
            userService.EditUserProfile(editReq);
            return ApiResponse.success(userService.getUserEntity());
        }catch (BaseException baseException){
            return ApiResponse.error(baseException.getErrorCode());
        }catch (Exception exception){
            System.out.println("unhandled exception :" + exception.getMessage());
            exception.printStackTrace();
            return ApiResponse.error(ErrorCode.INVALID);
        }
    }

    @GetMapping("/usernames/{username}")
    @ResponseBody
    public ApiResponse checkExistence(@PathVariable String username){
        return ApiResponse.success(userService.checkExistence(username));
    }

}
