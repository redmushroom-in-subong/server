package com.rms.drifeserver.domain.search.service;

import com.rms.drifeserver.domain.common.util.kakao.KaKaoSearchPlaceByKeyword;
import com.rms.drifeserver.domain.common.util.kakao.PlaceSearchRequest;
import com.rms.drifeserver.domain.search.dao.SearchQueryFactory;
import com.rms.drifeserver.domain.search.service.dto.SearchResponse;
import com.rms.drifeserver.domain.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    final private SearchQueryFactory searchQF;
    @Override
    public List<SearchResponse> searchByKeyword(PlaceSearchRequest placeSearchRequest) throws Exception {
        List<Long> resultIdList=KaKaoSearchPlaceByKeyword.searchPlaceByKeyWord(placeSearchRequest);
        String inQuery=resultIdList.toString().replace('[','(').replace(']',')');
        System.out.println("this is inQUery :::: " + inQuery);
        List<Store> storeList = searchQF.findSearchResult(resultIdList);
        System.out.println("!!");
        System.out.println("SearchResponse.ListOf(storeList) = " + SearchResponse.ListOf(storeList));
        return SearchResponse.ListOf(storeList);
    }
}
