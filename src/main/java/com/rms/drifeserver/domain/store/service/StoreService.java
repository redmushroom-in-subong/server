package com.rms.drifeserver.domain.store.service;

import com.rms.drifeserver.domain.store.dao.StoreRepository;
import com.rms.drifeserver.domain.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    public final StoreRepository storeRepository;

    //가게 정보 조회하기
    public Store getStore(Long storeId) {
        return null;
    }

    //가게 정보 간단 조회하기 - 방문수/리뷰수/단골수
    public Object getShortStore(Long storeId){
        return null;
    }

    //해당 가게에 대한 유저 정보 조회하기
    public void getUserInfoInStore(Long storeId, Long userId){
        
    }

    //해당 가게 리뷰 키워드 조회하기
    public void getReviewKeywordsInStore(Long storeId){

    }

    //해당 가게 단골 조회하기
    public void getRegularCustomersInStore(Long storeId){

    }

}
