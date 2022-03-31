package com.rms.drifeserver.domain.user.api;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import com.rms.drifeserver.domain.user.service.dto.request.EditProfileReq;
import com.rms.drifeserver.domain.user.service.dto.request.EditRegionReq;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;


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
        if(uId!=null){
            return ApiResponse.success(userService.getUserById(uId));
        }
        User user = userService.getUserEntity();
        return ApiResponse.success(user);
    }
    @PutMapping("")
    @ResponseBody
    public ApiResponse editUserInfo( @RequestBody EditProfileReq editReq){
        userService.editUserProfile(editReq);
        return ApiResponse.success(userService.getUserEntity());
    }

    @GetMapping("/usernames/{username}")
    @ResponseBody
    public ApiResponse checkExistence(@PathVariable String username){
        return ApiResponse.success(userService.checkExistence(username));
    }

    @GetMapping("/regions")
    @ResponseBody
    ApiResponse getRegionInfo(@RequestParam String xCor,@RequestParam String yCor) throws JSONException, ParseException {
        return ApiResponse.success(getRegionCodeByGeoLocation(xCor,yCor));
    }
    @PutMapping("/regions")
    @ResponseBody
    ApiResponse editRegionInfo(@RequestBody EditRegionReq editRegionReq){
        userService.editUserRegion(editRegionReq);
        return ApiResponse.success("success");
    }
}
