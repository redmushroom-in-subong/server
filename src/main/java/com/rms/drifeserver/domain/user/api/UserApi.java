package com.rms.drifeserver.domain.user.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import com.rms.drifeserver.domain.user.service.dto.request.EditProfileReq;
import com.rms.drifeserver.domain.user.service.dto.request.EditRegionReq;
import com.rms.drifeserver.domain.user.service.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import static com.rms.drifeserver.domain.common.dto.ApiResponse.success;
import static com.rms.drifeserver.domain.common.util.geolocation.KakaoGeoLocationApi.getRegionCodeByGeoLocation;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserApi {
    final private UserService userService;

    @GetMapping({"", "/{uId}"})
    @ResponseBody
    public ApiResponse getUserInfo(@PathVariable(required = false) Long uId){
        User user;
        if(uId!=null){
            user=userService.getUserById(uId);
        }else{
            user = userService.getUserEntity();
        }
        return success(UserInfo.of(user));
    }
    @PutMapping("")
    @ResponseBody
    public ApiResponse editUserInfo( @RequestBody EditProfileReq editReq){
        userService.editUserProfile(editReq);
        return success(UserInfo.of(userService.getUserEntity()));
    }

    @GetMapping("/usernames/{username}")
    @ResponseBody
    public ApiResponse checkExistence(@PathVariable String username){
        return success(userService.checkExistence(username));
    }

    @GetMapping("/regions")
    @ResponseBody
    ApiResponse getRegionInfo(@RequestParam String xCor,@RequestParam String yCor) throws JSONException, ParseException {
        return success(getRegionCodeByGeoLocation(xCor,yCor));
    }
    @PutMapping("/regions")
    @ResponseBody
    ApiResponse editRegionInfo(@RequestBody EditRegionReq editRegionReq){
        userService.editUserRegion(editRegionReq);
        return success("success");
    }
    @PostMapping("/withdrawal")
    @ResponseBody
    ApiResponse deleteUser(){
        return success("아직 개발 안됐으니까 나가지 말아보세요");
    }
}
