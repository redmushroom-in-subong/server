package com.rms.drifeserver.domain.user.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserApi {
    final private UserService userService;


    @GetMapping({"", "/{uId}"})
    @ResponseBody
    public ApiResponse userInfo(@PathVariable(required = false) Long uId){
        if(uId!=null){
            return ApiResponse.success(userService.getUserById(uId));
        }
        User user = userService.getUserEntity();
        return ApiResponse.success(user);
    }

}
