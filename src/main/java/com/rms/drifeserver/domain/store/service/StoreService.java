package com.rms.drifeserver.domain.store.service;

import com.rms.drifeserver.domain.store.dao.BusinessHoursRepository;
import com.rms.drifeserver.domain.store.dao.MenuRepository;
import com.rms.drifeserver.domain.store.dao.StoreRepository;
import com.rms.drifeserver.domain.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {
    public final StoreRepository storeRepository;
    public final MenuRepository menuRepository;
    public final BusinessHoursRepository businessHoursRepository;

    //가게 정보 조회하기
    @Transactional(readOnly = true)
    public Store getStore(Long storeId) {
        return storeRepository.findByKakaoPlaceId(storeId);
    }

    //가게 정보 간단 조회하기 - 방문수/리뷰수/단골수
    @Transactional(readOnly = true)
    public Object getShortStore(Long storeId){
        return null;
    }

    //해당 가게에 대한 유저 정보 조회하기
    @Transactional(readOnly = true)
    public void getUserInfoInStore(Long storeId, Long userId){
        
    }

    //해당 가게 리뷰 키워드 조회하기
    @Transactional(readOnly = true)
    public void getReviewKeywordsInStore(Long storeId){

    }

    //해당 가게 단골 조회하기
    @Transactional(readOnly = true)
    public void getRegularCustomersInStore(Long storeId){

    }

    //메뉴 조회하기
    public void getAllMenus(Long storeId){

    }

    //메뉴 추가하기
    public void addMenu(Long storeId){

    }

    //메뉴 수정하기
    public void updateMenu(Long storeId, Long menuId){

    }

    //메뉴 삭제하기
    public void deleteMenu(Long storeId, Long menuId){

    }

    //영업시간 추가하기
    public void addBusinessHours(Long stordId){
    }

    //영업시간 삭제하기
    public void deleteBusinessHours(Long storeId){

    }

}
