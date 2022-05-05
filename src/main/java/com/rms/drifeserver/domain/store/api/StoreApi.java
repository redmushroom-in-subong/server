package com.rms.drifeserver.domain.store.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.store.service.StoreService;
import com.rms.drifeserver.domain.store.service.dto.request.AddBusinessHoursRequest;
import com.rms.drifeserver.domain.store.service.dto.request.AddMenuRequest;
import com.rms.drifeserver.domain.store.service.dto.request.AddStoreDetailRequest;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stores")
public class StoreApi {
    private final StoreService storeService;
    private final UserService userService;

    @GetMapping("/pos")
    public ApiResponse getAllRegionStorePos(@RequestParam String regionCode){
        return ApiResponse.success(storeService.getAllRegionStorePos(regionCode));
    }

    @GetMapping("")
    public ApiResponse getStore(@RequestParam Long storeId, @RequestParam(required = false) Long themeId){
        if (themeId == null) { //가게 정보 조회하기
            return ApiResponse.success(storeService.getStore(storeId));
        }
        else { //테마별 가게 조회하기
            //return
        }
        return ApiResponse.error(ErrorCode.INVALID);
    }

    @GetMapping("/user-info") //해당 가게에 대한 유저 정보 조회하기
    public ApiResponse getUserInfoInStore(@RequestParam Long storeId) {
        return ApiResponse.success(storeService.getUserInfoInStore(storeId));
    }


    @GetMapping("/short-ver") //가게 정보 간단 조회하기
    public ApiResponse getShortStore(@RequestParam Long storeId){
        return ApiResponse.success(storeService.getShortStore(storeId));
    }

    @GetMapping("/keywords") //해당 가게 리뷰 키워드 조회하기
    public ApiResponse getReviewKeywordsInStore(@RequestParam Long storeId){
        return ApiResponse.success(storeService.getReviewKeywordsInStore(storeId));
    }

    @GetMapping("/regular-customers") //해당 가게 단골 조회하기
    public ApiResponse getRegularCustomersInStore(@RequestParam Long storeId){
        return ApiResponse.success(storeService.getRegularCustomersInStore(storeId));
    }

    @PostMapping("/{storeId}/like") //해당 가게 좋아요
    public ApiResponse toggleStoreLike(@PathVariable Long storeId){
        User user= userService.getUserEntity();
        storeService.toggleStoreLike(storeId, user);
        return ApiResponse.success(null);
    }

    @GetMapping("/menus")   //메뉴 조회하기
    public ApiResponse getMenus(@RequestParam Long storeId){
        return ApiResponse.success(storeService.getAllMenus(storeId));
    }

    @PostMapping("/{storeId}/menus")    //메뉴 추가하기
    public ApiResponse addMenu(@RequestBody AddMenuRequest req, @PathVariable Long storeId){
        return ApiResponse.success(storeService.addMenu(storeId, req));
    }

    @PutMapping("/{storeId}/menus/{menuId}")    //메뉴 수정하기
    public ApiResponse updateMenu(@RequestBody AddMenuRequest req, @PathVariable Long storeId, @PathVariable Long menuId){
        return ApiResponse.success(storeService.updateMenu(storeId, menuId, req));
    }

    @DeleteMapping("/{storeId}/menus/{menuId}")    //메뉴 삭제하기
    public ApiResponse deleteMenu(@PathVariable Long storeId, @PathVariable Long menuId){
        storeService.deleteMenu(storeId, menuId);
        return ApiResponse.success(null);
    }

    @PostMapping("/{storeId}/bhours")    //영업시간 추가하기
    public ApiResponse addBusinessHours(@RequestBody AddBusinessHoursRequest req, @PathVariable Long storeId){
        return ApiResponse.success(storeService.addBusinessHours(storeId, req));
    }

    @PutMapping("/{storeId}/bhours")    //영업시간 수정하기
    public ApiResponse updateBusinessHours(@RequestBody AddBusinessHoursRequest req, @PathVariable Long storeId){
        return ApiResponse.success(storeService.updateBusinessHours(storeId, req));
    }

    @PutMapping("/{storeId}/details")   //가게 상세정보 추가 및 수정하기
    public ApiResponse updateStoreDetail(@RequestBody AddStoreDetailRequest req, @PathVariable Long storeId){
        return ApiResponse.success(storeService.updateStoreDetail(storeId, req));
    }

}
