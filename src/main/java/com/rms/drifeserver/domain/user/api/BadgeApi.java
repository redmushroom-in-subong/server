package com.rms.drifeserver.domain.user.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class BadgeApi {
    final private UserService userService;

    @GetMapping("")
    @ResponseBody
    public ApiResponse testApi(@RequestHeader("Authorization") String tken){
        User user = userService.getUserEntity();
        return ApiResponse.success(user);
    }

}
