package com.rms.drifeserver.domain.store.service;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.service.ReviewKeywordService;
import com.rms.drifeserver.domain.review.service.ReviewServiceUtils;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordCountResponse;
import com.rms.drifeserver.domain.review.service.dto.response.StoreReviewCountInfoResponse;
import com.rms.drifeserver.domain.store.dao.BusinessHoursRepository;
import com.rms.drifeserver.domain.store.dao.MenuRepository;
import com.rms.drifeserver.domain.store.dao.StoreRepository;
import com.rms.drifeserver.domain.store.model.Menu;
import com.rms.drifeserver.domain.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final BusinessHoursRepository businessHoursRepository;
    private final VisitRepository visitRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewKeywordService reviewKeywordService;

    //가게 정보 조회하기
    public Store getStore(Long storeId) {
        return storeRepository.findByKakaoPlaceId(storeId);
    }

    //가게 정보 간단 조회하기 - 방문수/리뷰수/단골수
    public StoreReviewCountInfoResponse getShortStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        //Store store = storeRepository.findById(storeId).get();
        return ReviewServiceUtils.getStoreReviewCountInfo(visitRepository, reviewRepository, store);
    }

    //해당 가게에 대한 유저 정보 조회하기
    public void getUserInfoInStore(Long storeId, Long userId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));


    }

    //해당 가게 리뷰 키워드 조회하기
    public List<ReviewKeywordCountResponse> getReviewKeywordsInStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        return reviewKeywordService.getReviewKeywordCountByStore(store);
    }

    //해당 가게 단골 조회하기
    public void getRegularCustomersInStore(Long storeId){

    }

    //메뉴 조회하기
    public List<Menu> getAllMenus(Long storeId){
        return menuRepository.findAllByStoreId(storeId);
    }

    //메뉴 추가하기
    @Transactional
    public void addMenu(Long storeId){

    }

    //메뉴 수정하기
    @Transactional
    public void updateMenu(Long storeId, Long menuId){
        Menu findMenu = menuRepository.findByIdAndStoreId(menuId, storeId);

    }

    //메뉴 삭제하기
    @Transactional
    public void deleteMenu(Long storeId, Long menuId){
        Menu findMenu = menuRepository.findByIdAndStoreId(menuId, storeId);

    }

    //영업시간 추가하기
    @Transactional
    public void addBusinessHours(Long stordId){
    }

    //영업시간 삭제하기
    @Transactional
    public void deleteBusinessHours(Long storeId){

    }

}
