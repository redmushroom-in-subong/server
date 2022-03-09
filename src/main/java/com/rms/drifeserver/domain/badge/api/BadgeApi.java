package com.rms.drifeserver.domain.badge.api;

import com.rms.drifeserver.domain.badge.service.BadgeService;
import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/badge/abc")
@RequiredArgsConstructor
public class BadgeApi {
    final private UserService userService;
    final private BadgeService badgeService;
    @PostMapping("")
    @ResponseBody
    public ApiResponse addBadgeCode(){
        User user = userService.getUserEntity();
        List<UserBadgeResponse> ret=badgeService.findAllUserBadges(user.getId());
        return ApiResponse.success(ret);
    }
}
