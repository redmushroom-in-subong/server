package com.rms.drifeserver.domain.search.service.dto;

import com.rms.drifeserver.domain.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
public class SearchResponse {
    private Long storeId;
    private String x;
    private String y;
    private String storeName;

    static public List<SearchResponse> ListOf(List<Store> storeList) {
        List<SearchResponse> result = new ArrayList<>();
        for (Store store : storeList) {
            result.add(of(store));
        }
        return result;
    }
    static public SearchResponse of(Store store) {
        return new SearchResponse(store.getId(), store.getX(), store.getY(), store.getStoreName());
    }
}
