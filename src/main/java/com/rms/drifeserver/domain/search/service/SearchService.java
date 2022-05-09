package com.rms.drifeserver.domain.search.service;

import com.rms.drifeserver.domain.common.util.kakao.PlaceSearchRequest;
import com.rms.drifeserver.domain.search.service.dto.SearchResponse;

import java.util.List;

public interface SearchService {
    List<SearchResponse> searchByKeyword(PlaceSearchRequest placeSearchRequest) throws Exception;
}
