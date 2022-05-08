package com.rms.drifeserver.domain.badgecode.api;

import com.rms.drifeserver.domain.badgecode.service.BadgeCodeService;
import com.rms.drifeserver.domain.badgecode.service.dto.request.AddBadgeCodeRequest;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.rms.drifeserver.domain.common.dto.ApiResponse.*;

@RestController
@RequestMapping("v1/badge-code")
@RequiredArgsConstructor
public class BadgeCodeApi {
    final private BadgeCodeService badgeCodeService;
    @PostMapping("")
    @ResponseBody
    public ApiResponse addBadgeCode(@RequestBody AddBadgeCodeRequest addBadgeCodeRequest) throws BaseException,Exception{
        badgeCodeService.addBadgeCode(addBadgeCodeRequest);
        return success("success");
    }
}
