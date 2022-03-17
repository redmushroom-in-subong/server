package com.rms.drifeserver.domain.like.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/users/a")
@RequiredArgsConstructor
public class LikeApi {
    final private UserService userService;
    @GetMapping({"", "/{a}"})
    @ResponseBody
    public ApiResponse userInfo(@PathVariable(required = false) Long uId){
        try {
            return ApiResponse.success(0);
        } catch (BaseException baseException) {
            return ApiResponse.error(baseException.getErrorCode());
        } catch (Exception exception) {
            return ApiResponse.error(ErrorCode.INVALID);
        }
    }
}
