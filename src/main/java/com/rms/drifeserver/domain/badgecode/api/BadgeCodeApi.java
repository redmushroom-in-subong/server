package com.rms.drifeserver.domain.badgecode.api;

import com.rms.drifeserver.domain.badgecode.service.BadgeCodeService;
import com.rms.drifeserver.domain.badgecode.service.dto.request.AddBadgeCodeRequest;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("v1/badge-code")
@RequiredArgsConstructor
public class BadgeCodeApi {
    final private BadgeCodeService badgeCodeService;
    @PostMapping("")
    @ResponseBody
    public ApiResponse addBadgeCode(@RequestBody AddBadgeCodeRequest addBadgeCodeRequest){
        try{
            badgeCodeService.addBadgeCode(addBadgeCodeRequest);
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
