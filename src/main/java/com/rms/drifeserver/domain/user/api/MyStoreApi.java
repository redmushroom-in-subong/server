package com.rms.drifeserver.domain.user.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.service.MyStoreService;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rms.drifeserver.domain.common.dto.ApiResponse.*;

@RestController
@RequestMapping("v1/users/stores")
@RequiredArgsConstructor
public class MyStoreApi {
    final private MyStoreService myStoreService;
    @GetMapping("/recent-visited")
    @ResponseBody
    public ApiResponse getMyRecentStore(){
        return success(myStoreService.getMyRecentStores());
    }
    @GetMapping("/favorite-place")
    @ResponseBody
    public ApiResponse getMyFavoriteStore(){
        return success(myStoreService.getMyFavoriteStores());
    }
}
