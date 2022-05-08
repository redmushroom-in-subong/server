package com.rms.drifeserver.domain.badge.api;

import com.rms.drifeserver.domain.badge.service.BadgeService;
import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rms.drifeserver.domain.common.dto.ApiResponse.*;

@Controller
@RequestMapping("v1/users/badges")
@RequiredArgsConstructor
public class BadgeApi {
    final private UserServiceImpl userService;
    final private BadgeService badgeService;
    @GetMapping("")
    @ResponseBody
    public ApiResponse getUserAllBadges() throws Exception{
        List<UserBadgeResponse> ret=badgeService.findAllUserBadges();
        return success(ret);
    }
    @PutMapping("")
    @ResponseBody
    public ApiResponse editBadge(@RequestParam(name="badgeCode")Long badgeId)throws Exception{
        userService.editUsingBadge(badgeId);
        return success("success");
    }
}
