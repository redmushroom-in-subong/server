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
import com.rms.drifeserver.domain.store.model.BusinessHours;
import com.rms.drifeserver.domain.store.model.Menu;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.store.service.dto.request.AddBusinessHoursRequest;
import com.rms.drifeserver.domain.store.service.dto.request.AddMenuRequest;
import com.rms.drifeserver.domain.store.service.dto.response.BusinessHoursResponse;
import com.rms.drifeserver.domain.store.service.dto.response.MenuResponse;
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
    public List<MenuResponse> getAllMenus(Long storeId){
        return menuRepository.findAllByStoreId(storeId);
    }

    //메뉴 추가하기
    @Transactional
    public MenuResponse addMenu(Long storeId, AddMenuRequest req){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        Menu menu = req.toMenu(store);
        menuRepository.save(menu);
        return MenuResponse.of(menu);
    }

    //메뉴 수정하기
    @Transactional
    public MenuResponse updateMenu(Long storeId, Long menuId, AddMenuRequest req){
        Menu findMenu = menuRepository.findByIdAndStoreId(menuId, storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_MENU));
        if(req.getItem()==null && req.getPrice()==null){
            throw new BaseException(ErrorCode.INVALID);
        } else if(req.getPrice()==null){
            findMenu.updateItem(req.getItem());
        } else if(req.getItem()==null){
            findMenu.updatePrice(req.getPrice());
        } else{
            findMenu.updateItem(req.getItem());
            findMenu.updatePrice(req.getPrice());
        }
        menuRepository.save(findMenu);
        return MenuResponse.of(findMenu);
    }

    //메뉴 삭제하기
    @Transactional
    public void deleteMenu(Long storeId, Long menuId){
        Menu findMenu = menuRepository.findByIdAndStoreId(menuId, storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_MENU));
        menuRepository.delete(findMenu);
    }

    //영업시간 추가하기
    @Transactional
    public BusinessHoursResponse addBusinessHours(Long storeId, AddBusinessHoursRequest req){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        BusinessHours bhours = req.toBhours(store);
        businessHoursRepository.save(bhours);
        return BusinessHoursResponse.of(bhours);
    }

    //영업시간 수정하기
    @Transactional
    public void updateBusinessHours(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));

    }


}
