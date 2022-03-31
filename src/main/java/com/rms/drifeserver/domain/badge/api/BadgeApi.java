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
@RequestMapping("v1/users/badges")
@RequiredArgsConstructor
public class BadgeApi {
    final private UserService userService;
    final private BadgeService badgeService;
    @GetMapping("")
    @ResponseBody
    public ApiResponse getUserAllBadges() throws Exception{
        List<UserBadgeResponse> ret=badgeService.findAllUserBadges();
        return ApiResponse.success(ret);
    }
    @PutMapping("")
    @ResponseBody
    public ApiResponse editBadge(@RequestParam(name="badgeCode")Long badgeId)throws Exception{
        userService.editUsingBadge(badgeId);
        return ApiResponse.success("success");
    }
}
