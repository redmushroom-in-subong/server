package com.rms.drifeserver.domain.store.service;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.like.dao.StoreLikesRepository;
import com.rms.drifeserver.domain.like.model.StoreLikes;
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
import com.rms.drifeserver.domain.store.model.Tier;
import com.rms.drifeserver.domain.store.service.dto.request.AddBusinessHoursRequest;
import com.rms.drifeserver.domain.store.service.dto.request.AddMenuRequest;
import com.rms.drifeserver.domain.store.service.dto.request.AddStoreDetailRequest;
import com.rms.drifeserver.domain.store.service.dto.response.*;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import com.rms.drifeserver.domain.user.service.dto.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final BusinessHoursRepository businessHoursRepository;
    private final VisitRepository visitRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReviewKeywordService reviewKeywordService;
    private final StoreLikesRepository storeLikesRepository;
    private final UserService userService;

    //법정동 코드로 가게 좌표 조회
    @Transactional(readOnly = true)
    public List<StorePosResponse> getAllRegionStorePos(String regionCode){
        return storeRepository.findAllByRegionCode(regionCode);
    }

    //가게 정보 조회하기
    @Transactional(readOnly = true)
    public StoreResponse getStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        return StoreResponse.of(store);
    }

    //가게 정보 간단 조회하기 - 방문수/리뷰수/단골수
    @Transactional(readOnly = true)
    public StoreReviewCountInfoResponse getShortStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        return ReviewServiceUtils.getStoreReviewCountInfo(visitRepository, reviewRepository, store);
    }

    //해당 가게에 대한 유저 정보 조회하기
    @Transactional(readOnly = true)
    public UserInStoreResponse getUserInfoInStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        User user = userService.getUserEntity();
        Long visitCnt = visitRepository.countByStoreAndUser(store, user);
        Long reviewCnt = reviewRepository.countByStoreAndUser(store, user);
        return UserInStoreResponse.of(store, user, visitCnt, reviewCnt);
    }

    //해당 가게 리뷰 키워드 조회하기
    @Transactional(readOnly = true)
    public List<ReviewKeywordCountResponse> getReviewKeywordsInStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        return reviewKeywordService.getReviewKeywordCountByStore(store);
    }

    //해당 가게 단골 조회하기
    @Transactional(readOnly = true)
    public List<UserInStoreResponse> getRegularCustomersInStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        List<User> regCustom = userRepository.findRegCustom(storeId);
        List<UserInStoreResponse> ret = new ArrayList<>();
        for(User u : regCustom){
            ret.add(UserInStoreResponse.of(store, u,
                    visitRepository.countByStoreAndUser(store, u),
                    reviewRepository.countByStoreAndUser(store, u)));
        }
        return ret;
    }

    //해당 가게 좋아요
    public void toggleStoreLike(Long storeId, User user){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        StoreLikes storeLikes = storeLikesRepository.findByUserAndStore(user, store);

        if (storeLikes != null) {
            storeLikesRepository.delete(storeLikes);
        } else{
            storeLikesRepository.save(StoreLikes.of(user, store));
        }
    }

    //메뉴 조회하기
    @Transactional(readOnly = true)
    public List<MenuResponse> getAllMenus(Long storeId){
        return menuRepository.findAllByStoreId(storeId);
    }

    //메뉴 추가하기
    public MenuResponse addMenu(Long storeId, AddMenuRequest req){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        Menu menu = req.toMenu(store);
        menuRepository.save(menu);
        return MenuResponse.of(menu);
    }

    //메뉴 수정하기
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
    public void deleteMenu(Long storeId, Long menuId){
        Menu findMenu = menuRepository.findByIdAndStoreId(menuId, storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_MENU));
        menuRepository.delete(findMenu);
    }

    //영업시간 추가하기
    public BusinessHoursResponse addBusinessHours(Long storeId, AddBusinessHoursRequest req){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        BusinessHours bhours = req.toBhours(store);
        businessHoursRepository.save(bhours);
        return BusinessHoursResponse.of(bhours);
    }

    //영업시간 수정하기
    public BusinessHoursResponse updateBusinessHours(Long storeId, AddBusinessHoursRequest req){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));
        BusinessHours bhours = store.getBusinessHours();
        bhours.updateBhours(req.getMon(),req.getTue(),req.getWed(),req.getThu(),
                req.getFri(),req.getSat(),req.getSun());
        businessHoursRepository.save(bhours);
        return BusinessHoursResponse.of(bhours);
    }

    //가게 상세정보 추가 및 수정하기
    public StoreDetailResponse updateStoreDetail(Long storeId, AddStoreDetailRequest req){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_STORE));

        if(req.getStoreDesc()==null && req.getSnsAddress()==null){
            throw new BaseException(ErrorCode.INVALID);
        } else if(req.getSnsAddress()==null){
            store.updateDesc(req.getStoreDesc());
        } else if(req.getStoreDesc()==null){
            store.updateSnsAdr(req.getSnsAddress());
        } else{
            store.updateDesc(req.getStoreDesc());
            store.updateSnsAdr(req.getSnsAddress());
        }
        storeRepository.save(store);
        return StoreDetailResponse.of(store);
    }
}
