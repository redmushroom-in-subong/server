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

import javax.websocket.server.PathParam;
import java.util.List;

import static com.rms.drifeserver.domain.common.exception.type.ErrorCode.*;
import static com.rms.drifeserver.domain.common.util.geolocation.KakaoGeoLocationApi.getRegionCodeByGeoLocation;

@RestController
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
            return ApiResponse.error(INVALID);
        }
    }
    @PutMapping("")
    @ResponseBody
    public ApiResponse editUserInfo( @RequestBody EditProfileReq editReq){
        try{
            userService.editUserProfile(editReq);
            return ApiResponse.success(userService.getUserEntity());
        }catch (BaseException baseException){
            return ApiResponse.error(baseException.getErrorCode());
        }catch (Exception exception){
            System.out.println("unhandled exception :" + exception.getMessage());
            exception.printStackTrace();
            return ApiResponse.error(INVALID);
        }
    }

    @GetMapping("/usernames/{username}")
    @ResponseBody
    public ApiResponse checkExistence(@PathVariable String username){
        return ApiResponse.success(userService.checkExistence(username));
    }

    @GetMapping("/regions")
    @ResponseBody
    ApiResponse getRegionInfo(@RequestParam String xCor,@RequestParam String yCor){
        try{
            System.out.println("x = " + xCor);
            System.out.println("y = " + yCor);
            return ApiResponse.success(getRegionCodeByGeoLocation(xCor,yCor));
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponse.error(INVALID);
        }

    }
}
