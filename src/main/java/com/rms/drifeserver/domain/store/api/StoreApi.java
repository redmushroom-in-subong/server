package com.rms.drifeserver.domain.store.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stores")
public class StoreApi {
    private final StoreService storeService;

    @GetMapping("")
    public ApiResponse getStore(@RequestParam Long storeId, @RequestParam(required = false) Long userId, @RequestParam(required = false) Long themeId){
        if (userId == null && themeId == null) { //가게 정보 조회하기
            return ApiResponse.success(storeService.getStore(storeId));
        }
        else if (userId == null) { //테마별 가게 조회하기
            //return
        }
        else if (themeId == null) { //해당 가게에 대한 유저 정보 조회하기

        }
        return ApiResponse.error(ErrorCode.INVALID);

    }

    @GetMapping("/short-ver") //가게 정보 간단 조회하기
    public ApiResponse getShortStore(@RequestParam Long storeId){
        return ApiResponse.success(storeService.getShortStore(storeId));
    }

    @GetMapping("/keywords") //해당 가게 리뷰 키워드 조회하기
    public ApiResponse getReviewKeywordsInStore(@RequestParam Long storeId){
        return null;
    }

    @GetMapping("/regular-customers") //해당 가게 단골 조회하기
    public ApiResponse getRegularCustomersInStore(@RequestParam Long storeId){
        return null;
    }

}
